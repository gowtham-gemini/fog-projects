package com.assistanz.fogpanel.licensemanager

import com.assistanz.fogpanel.licensemanager.*;
import java.util.regex.Matcher
import java.util.regex.Pattern
import org.springframework.security.access.annotation.Secured

@Secured(['ROLE_ADMIN'])
class SearchController {
    
    def results() {
        
        HashMap items = new HashMap()
        
        if(params.menuName == "All") {
            
            def productList = Product.withCriteria {
                or {
                    ilike('name', '%' + params.name + '%')
                    ilike('description', '%' + params.name + '%') 
                    ilike('code', '%' + params.name + '%') 
                    ilike('url', '%' + params.name + '%')
                    if(params.name.matches([".*[1-9].*"])) {
                        eq('licenseQuantity', Long.parseLong(params.name)) 
                    }
                }
            }
            
            items.put("productList", productList.size())
            
            def userList = User.withCriteria {
                or {
                    ilike('username', '%' + params.name + '%')
                    ilike('firstName', '%' + params.name + '%') 
                    ilike('companyName', '%' + params.name + '%') 
                    ilike('lastName', '%' + params.name + '%') 
                }
            }
            
            items.put("userList", userList.size())
            
            def releaseList = Release.withCriteria {
                or {
                    
                    ilike('productVersion', '%' + params.name + '%') 
                    
                    if(Product.findAllByNameLike('%' + params.name + '%')) {
                            'in'("product", Product.findAllByNameLike('%' + params.name + '%'))  
                    }  
                }
            }
            
            items.put("releaseList", releaseList.size())
            
            def productInstanceList = ProductInstance.withCriteria {
                or {
                    if(params.name.matches([".*[1-9].*"])){
                        eq("id", Long.parseLong(params.name))
                    }
                    
                    ilike('name', '%' + params.name + '%')
                    
                    if(User.findAllByUsernameLike('%' + params.name + '%')) {
                        'in'("user", User.findAllByUsernameLike('%' + params.name + '%'))    
                    }
                    if(Product.findAllByNameLike('%' + params.name + '%')) {
                        'in'("product", Product.findAllByNameLike('%' + params.name + '%'))  
                    }  
                }
            }
            
            items.put("productInstanceList", productInstanceList.size())           
            render(view: "allResult", model: [counts: items, value : params.name]) 
        }
        
       else if(params.menuName == "Product") {
           
            def productResult = Product.withCriteria {
                
                or {
                    ilike('name', '%' + params.name + '%')
                    ilike('description', '%' + params.name + '%') 
                    ilike('code', '%' + params.name + '%') 
                    ilike('url', '%' + params.name + '%') 
                    if(params.name.matches([".*[1-9].*"])) {
                        eq('licenseQuantity', Long.parseLong(params.name)) 
                    }
                }
            }
            render(view: "productResult", model: [results: productResult])
            
        } else if(params.menuName == "User") {
            
            def userResult = User.withCriteria {
                or {
                    ilike('username', '%' + params.name + '%')
                    ilike('firstName', '%' + params.name + '%') 
                    ilike('companyName', '%' + params.name + '%') 
                    ilike('lastName', '%' + params.name + '%') 
                }
            }
            render(view: "userResult", model: [results: userResult])
            
        } else if(params.menuName == "Release") {
            
            def releaseResult = Release.withCriteria {
                or {
                    ilike('productVersion', '%' + params.name + '%') 
                    if(Product.findAllByNameLike('%' + params.name + '%')) {
                        'in'("product", Product.findAllByNameLike('%' + params.name + '%'))  
                    }   
                }
            }
            render(view: "releaseResult", model: [results: releaseResult]) 
            
        } else if(params.menuName == "Product Instance") {
            
            def productInstanceResult = ProductInstance.withCriteria {
                or {
                    if(params.name.matches([".*[1-9].*"])){
                        eq("id", Long.parseLong(params.name))
                    } 
                    
                    ilike('name', '%' + params.name + '%')
                    
                    if(User.findAllByUsernameLike('%' + params.name + '%')) {
                        'in'("user", User.findAllByUsernameLike('%' + params.name + '%'))    
                    }
                    
                    if(Product.findAllByNameLike('%' + params.name + '%')) {
                        'in'("product", Product.findAllByNameLike('%' + params.name + '%'))  
                    }  
                }
            }
            render(view: "productInstanceResult", model: [results: productInstanceResult]) 
        }
    }
}

dataSource {
        pooled = false
}

hibernate {

    dialect='org.hibernate.dialect.MySQL5Dialect' // Mandatory
    
    //false
    cache.use_second_level_cache=true
    //false
    cache.use_query_cache=true

    cache.provider_class='net.sf.ehcache.hibernate.EhCacheProvider'

}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = ""
            driverClassName = "com.mysql.jdbc.Driver"
            url = "jdbc:mysql://localhost/manager"
            username = "root"
            password = "l3tm3in"
        }
    }
    test {
        dataSource {
            dbCreate = ""
	}
    }
    production {
        dataSource {
                dbCreate = ""
                jndiName = "java:comp/env/jdbc/FogPanelLicenseManagerDataSource"

        }
    }
}

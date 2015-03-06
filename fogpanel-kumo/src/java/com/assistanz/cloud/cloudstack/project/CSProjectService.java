package com.assistanz.cloud.cloudstack.project;

import java.util.HashMap;
import java.util.LinkedList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import org.apache.commons.httpclient.NameValuePair;

/**
 * 
 * @author Gowtham
 *
 */
public class CSProjectService {
	
	private CloudStackServer server;
	
	public CSProjectService(CloudStackServer server) {
        this.server = server;
	}
	
	/**
	 * Creates a project
	 * 
	 * @param projectDisplayText The display text of the project
	 * @param projectName The name of the project
	 * @param optional
	 * @return
	 * @throws Exception
	 */
    public CreateProjectResponse createProject(String projectDisplayText, 
    		String projectName, HashMap<String,String> optional) 
    				throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("createProject", optional);
        arguments.add(new NameValuePair("displaytext", projectDisplayText));
        arguments.add(new NameValuePair("name", projectName));
        
        Document responseDocument = server.makeRequest(arguments);

        return getCreateProjectResponse(responseDocument);
    }
  
    /**
     * Converts XML document into CreateProjectResponse object
     * 
     * @param doc
     * @return 
     */
    private CreateProjectResponse getCreateProjectResponse(Document doc) {
    	CreateProjectResponse response = new CreateProjectResponse();
        
        // get id from XML and set as the id of the project
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getNodeValue());
        }
        
        // get account from XML and set as the account name of the project's owner
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectAccount(node.getNodeValue());
        }
        
        // get displaytext from XML and set as the displaytext of the project
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectDisplayText(node.getNodeValue());
        }
        
        // get domain from XML and set as the domain name where the project belongs to
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectDomain(node.getNodeValue());
        }
        
        // get domainid from XML and set as the domain id the project belongs to
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectDomainId(node.getNodeValue());
        }
        
        // get name from XML and set as the name of the project
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectName(node.getNodeValue());
        }
        
        // get state from XML and set as the state of the project
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectState(node.getNodeValue());
        }
        
        return response;
    }
    
    /**
     * Deletes a project
     * 
     * @param projectId The id of the project to be deleted
     * @return
     * @throws Exception
     */
    public DeleteProjectResponse deleteProject(String projectId)
    		throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("deleteProject", null);
        arguments.add(new NameValuePair("id", projectId));
        
        Document responseDocument = server.makeRequest(arguments);

        return getDeleteProjectResponse(responseDocument);
        
    }
    
    /**
      * Converts XML document into DeleteProjectResponse object
      * 
      * @param doc
      * @return 
      */
    private DeleteProjectResponse getDeleteProjectResponse(Document doc) {
    	DeleteProjectResponse response = new DeleteProjectResponse();
        
        // get displaytext from XML and set Any text associated with the success or 
        // failure on Deleting a project
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getNodeValue());
        }
        
        // get success from XML and set Return true if Delete project operation 
        // is executed successfully
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getNodeValue());
        }
        
         return response;
    }
    
    /**
     * Updates a project
     * 
     * @param projectId The id of the project to be modified
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateProjectResponse updateProject(String projectId, 
    		 HashMap<String,String> optional) 
    				throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("updateProject", optional);
        arguments.add(new NameValuePair("id", projectId));
        
        Document responseDocument = server.makeRequest(arguments);

        return getUpdateProjectResponse(responseDocument);
    }
  
    /**
     * Converts XML document into UpdateProjectResponse object
     * 
     * @param doc
     * @return 
     */
    private UpdateProjectResponse getUpdateProjectResponse(Document doc) {
    	UpdateProjectResponse response = new UpdateProjectResponse();
        
        // get id from XML and set as the id of the project
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getNodeValue());
        }
        
        // get account from XML and set as the account name of the project's owner
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectAccount(node.getNodeValue());
        }
        
        // get displaytext from XML and set as the displaytext of the project
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectDisplayText(node.getNodeValue());
        }
        
        // get domain from XML and set as the domain name where the project belongs to
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectDomain(node.getNodeValue());
        }
        
        // get domainid from XML and set as the domain id the project belongs to
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectDomainId(node.getNodeValue());
        }
        
        // get name from XML and set as the name of the project
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectName(node.getNodeValue());
        }
        
        // get state from XML and set as the state of the project
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectState(node.getNodeValue());
        }
        
        return response;
    }
    
    /**
     * Activates a project
     * 
     * @param projectId the id of the project to be Activated
     * @return
     * @throws Exception
     */
    public ActivateProjectResponse activateProject(String projectId) 
   				throws Exception {
       
       LinkedList<NameValuePair> arguments = 
               server.getDefaultQuery("activateProject", null);
       arguments.add(new NameValuePair("id", projectId));
       
       Document responseDocument = server.makeRequest(arguments);

       return getActivateProjectResponse(responseDocument);
   }
 
   /**
    * Converts XML document into ActivateProjectResponse object
    * 
    * @param doc
    * @return 
    */
   private ActivateProjectResponse getActivateProjectResponse(Document doc) {
	   ActivateProjectResponse response = new ActivateProjectResponse();
       
       // get id from XML and set as the id of the project
       NodeList list = doc.getElementsByTagName("id");
       if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setProjectId(node.getNodeValue());
       }
       
       // get account from XML and set as the account name of the project's owner
       list = doc.getElementsByTagName("account");
       if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setProjectAccount(node.getNodeValue());
       }
       
       // get displaytext from XML and set as the displaytext of the project
       list = doc.getElementsByTagName("displaytext");
       if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setProjectDisplayText(node.getNodeValue());
       }
       
       // get domain from XML and set as the domain name where the project belongs to
       list = doc.getElementsByTagName("domain");
       if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setProjectDomain(node.getNodeValue());
       }
       
       // get domainid from XML and set as the domain id the project belongs to
       list = doc.getElementsByTagName("domainid");
       if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setProjectDomainId(node.getNodeValue());
       }
       
       // get name from XML and set as the name of the project
       list = doc.getElementsByTagName("name");
       if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setProjectName(node.getNodeValue());
       }
       
       // get state from XML and set as the state of the project
       list = doc.getElementsByTagName("state");
       if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setProjectState(node.getNodeValue());
       }
       
       return response;
   }
   
   /**
    * Suspends a project The id of the project to be suspended
    * 
    * @param projectId
    * @return
    * @throws Exception
    */
   public SuspendProjectResponse suspendProject(String projectId) 
				throws Exception {
  
	  LinkedList<NameValuePair> arguments = 
	          server.getDefaultQuery("suspendProject", null);
	  arguments.add(new NameValuePair("id", projectId));
	  
	  Document responseDocument = server.makeRequest(arguments);
	
	  return getSuspendProjectResponse(responseDocument);
	}

	/**
	* Converts XML document into SuspendProjectResponse object
	* 
	* @param doc
	* @return 
	*/
	private SuspendProjectResponse getSuspendProjectResponse(Document doc) {
		SuspendProjectResponse response = new SuspendProjectResponse();
	  
	  // get id from XML and set as the id of the project
	  NodeList list = doc.getElementsByTagName("id");
	  if (list.getLength() > 0) {
	      Node node = list.item(0);
	      response.setProjectId(node.getNodeValue());
	  }
	  
	  // get account from XML and set as the account name of the project's owner
	  list = doc.getElementsByTagName("account");
	  if (list.getLength() > 0) {
	      Node node = list.item(0);
	      response.setProjectAccount(node.getNodeValue());
	  }
	  
	  // get displaytext from XML and set as the displaytext of the project
	  list = doc.getElementsByTagName("displaytext");
	  if (list.getLength() > 0) {
	      Node node = list.item(0);
	      response.setProjectDisplayText(node.getNodeValue());
	  }
	  
	  // get domain from XML and set as the domain name where the project belongs to
	  list = doc.getElementsByTagName("domain");
	  if (list.getLength() > 0) {
	      Node node = list.item(0);
	      response.setProjectDomain(node.getNodeValue());
	  }
	  
	  // get domainid from XML and set as the domain id the project belongs to
	  list = doc.getElementsByTagName("domainid");
	  if (list.getLength() > 0) {
	      Node node = list.item(0);
	      response.setProjectDomainId(node.getNodeValue());
	  }
	  
	  // get name from XML and set as the name of the project
	  list = doc.getElementsByTagName("name");
	  if (list.getLength() > 0) {
	      Node node = list.item(0);
	      response.setProjectName(node.getNodeValue());
	  }
	  
	  // get state from XML and set as the state of the project
	  list = doc.getElementsByTagName("state");
	  if (list.getLength() > 0) {
	      Node node = list.item(0);
	      response.setProjectState(node.getNodeValue());
	  }
	  
	  return response;
	}
	
	/**
	 * Lists projects and provides detailed information for listed projects
	 * 
	 * @param optional
	 * @return
	 * @throws Exception
	 */
    public ListProjectsResponse listProjects(HashMap<String,String> optional) 
    				throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("listProjects", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListProjectsResponse(responseDocument);
    }
  
    /**
     * Converts XML document into ListProjectsResponse object
     * 
     * @param doc
     * @return 
     */
    private ListProjectsResponse getListProjectsResponse(Document doc) {
    	ListProjectsResponse response = new ListProjectsResponse();
        
        // get id from XML and set as the id of the project
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getNodeValue());
        }
        
        // get account from XML and set as the account name of the project's owner
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectAccount(node.getNodeValue());
        }
        
        // get displaytext from XML and set as the displaytext of the project
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectDisplayText(node.getNodeValue());
        }
        
        // get domain from XML and set as the domain name where the project belongs to
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectDomain(node.getNodeValue());
        }
        
        // get domainid from XML and set as the domain id the project belongs to
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectDomainId(node.getNodeValue());
        }
        
        // get name from XML and set as the name of the project
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectName(node.getNodeValue());
        }
        
        // get state from XML and set as the state of the project
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectState(node.getNodeValue());
        }
        
        return response;
    }
    
    /**
     * Lists projects and provides detailed information for listed projects
     * 
     * @param optional
     * @return
     * @throws Exception
     */
    public ListProjectInvitationsResponse listProjectInvitations(
    		HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("listProjectInvitations", optional);
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getListProjectInvitationsResponse(responseDocument);
	}

	/**
	* Converts XML document into ListProjectInvitationsResponse object
	* 
	* @param doc
	* @return 
	*/
	private ListProjectInvitationsResponse getListProjectInvitationsResponse(Document doc) {
		ListProjectInvitationsResponse response = new ListProjectInvitationsResponse();
	
		// get id from XML and set as the id of the project
		NodeList list = doc.getElementsByTagName("id");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setProjectId(node.getNodeValue());
		}
		
		// get account from XML and set as the account name of the project's owner
		list = doc.getElementsByTagName("account");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setProjectAccount(node.getNodeValue());
		}
		
		// get displaytext from XML and set as the displaytext of the project
		list = doc.getElementsByTagName("displaytext");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setProjectDisplayText(node.getNodeValue());
		}
		
		// get domain from XML and set as the domain name where the project belongs to
		list = doc.getElementsByTagName("domain");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setProjectDomain(node.getNodeValue());
		}
		
		// get domainid from XML and set as the domain id the project belongs to
		list = doc.getElementsByTagName("domainid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setProjectDomainId(node.getNodeValue());
		}
		
		// get name from XML and set as the name of the project
		list = doc.getElementsByTagName("name");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setProjectName(node.getNodeValue());
		}
		
		// get state from XML and set as the state of the project
		list = doc.getElementsByTagName("state");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setProjectState(node.getNodeValue());
		}
	
		return response;
	}
	
	/**
	 * Accepts or declines project invitation
	 * 
	 * @param projectId The	id of the project to join
	 * @param optional
	 * @return
	 * @throws Exception
	 */
    public UpdateProjectInvitationResponse updateProjectInvitation(String projectId, 
    		HashMap<String,String> optional)throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("updateProjectInvitation", null);
        arguments.add(new NameValuePair("projectid", projectId));
        
        Document responseDocument = server.makeRequest(arguments);

        return getUpdateProjectInvitationResponse(responseDocument);
        
    }
    
    /**
      * Converts XML document into UpdateProjectInvitationResponse object
      * 
      * @param doc
      * @return 
      */
    private UpdateProjectInvitationResponse getUpdateProjectInvitationResponse(Document doc) {
    	UpdateProjectInvitationResponse response = new UpdateProjectInvitationResponse();
        
        // get displaytext from XML and set Any text associated with the success or 
        // failure on updating  project invitation
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getNodeValue());
        }
        
        // get success from XML and set Return true if update project invitation operation 
        // is executed successfully
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getNodeValue());
        }
        
         return response;
    }
    
    /**
     * declines project invitation
     * 
     * @param projectId id of the invitation
     * @param optional
     * @return
     * @throws Exception
     */
    public DeleteProjectInvitationResponse deleteProjectInvitation(String projectId, 
    		HashMap<String,String> optional)throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("deleteProjectInvitation", null);
        arguments.add(new NameValuePair("id", projectId));
        
        Document responseDocument = server.makeRequest(arguments);

        return getDeleteProjectInvitationResponse(responseDocument);
        
    }
    
    /**
      * Converts XML document into DeleteProjectInvitationResponse object
      * 
      * @param doc
      * @return 
      */
    private DeleteProjectInvitationResponse getDeleteProjectInvitationResponse(Document doc) {
    	DeleteProjectInvitationResponse response = new DeleteProjectInvitationResponse();
        
        // get displaytext from XML and set Any text associated with the success or 
        // failure on deleting  project invitation
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getNodeValue());
        }
        
        // get success from XML and set Return true if delete project invitation operation 
        // is executed successfully
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getNodeValue());
        }
        
         return response;
    }
}

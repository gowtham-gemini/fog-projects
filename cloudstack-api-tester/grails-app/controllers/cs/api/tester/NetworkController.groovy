package cs.api.tester

class NetworkController {

    def dedicatePublicIpRange() { render(view: "dedicatePublicIpRange", model: [menuDedicate: "highlight",  apiUrl: "/api/admin/Network/dedicatePublicIpRange", method: "GET"]);}
     
    def releasePublicIpRange() {  render(view: "releasePublicIpRange", model: [menuRelease: "highlight",  apiUrl: "/api/admin/Network/releasePublicIpRange", method: "GET"]); }
    
    def createNetwork() {  render(view: "createNetwork", model: [menuRelease: "highlight",  apiUrl: "/api/admin/Network/createNetwork", method: "GET"]); }
    
    def deleteNetwork() {  render(view: "deleteNetwork", model: [menuRelease: "highlight",  apiUrl: "/api/admin/Network/deleteNetwork", method: "GET"]); }
    
    def listNetworks() {  render(view: "listNetworks", model: [menuRelease: "highlight",  apiUrl: "/api/admin/Network/listNetworks", method: "GET"]); }
    
    def restartNetwork() {  render(view: "restartNetwork", model: [menuRelease: "highlight",  apiUrl: "/api/admin/Network/restartNetwork", method: "GET"]); }

    def updateNetwork() {  render(view: "updateNetwork", model: [menuRelease: "highlight",  apiUrl: "/api/admin/Network/updateNetwork", method: "GET"]); }
 
    def createPhysicalNetwork() {  render(view: "createPhysicalNetwork", model: [menuRelease: "highlight",  apiUrl: "/api/admin/Network/createPhysicalNetwork", method: "GET"]); }

    def deletePhysicalNetwork() {  render(view: "deletePhysicalNetwork", model: [menuRelease: "highlight",  apiUrl: "/api/admin/Network/deletePhysicalNetwork", method: "GET"]); }
    
    def listPhysicalNetworks() {  render(view: "listPhysicalNetworks", model: [menuRelease: "highlight",  apiUrl: "/api/admin/Network/listPhysicalNetworks", method: "GET"]); }
    
    def updatePhysicalNetwork() {  render(view: "updatePhysicalNetwork", model: [menuRelease: "highlight",  apiUrl: "/api/admin/Network/updatePhysicalNetwork", method: "GET"]); }
    
    def listSupportedNetworkServices() {  render(view: "listSupportedNetworkServices", model: [menuRelease: "highlight",  apiUrl: "/api/admin/Network/listSupportedNetworkServices", method: "GET"]); }
    
    def addNetworkServiceProvider() {  render(view: "addNetworkServiceProvider", model: [menuRelease: "highlight",  apiUrl: "/api/admin/Network/addNetworkServiceProvider", method: "GET"]); }
    
    def deleteNetworkServiceProvider() {  render(view: "deleteNetworkServiceProvider", model: [menuRelease: "highlight",  apiUrl: "/api/admin/Network/deleteNetworkServiceProvider", method: "GET"]); }
    
    def listNetworkServiceProviders() {  render(view: "listNetworkServiceProviders", model: [menuRelease: "highlight",  apiUrl: "/api/admin/Network/listNetworkServiceProviders", method: "GET"]); }
    
    def updateNetworkServiceProvider() {  render(view: "updateNetworkServiceProvider", model: [menuRelease: "highlight",  apiUrl: "/api/admin/Network/updateNetworkServiceProvider", method: "GET"]); }
    
    def createStorageNetworkIpRange() {  render(view: "createStorageNetworkIpRange", model: [menuRelease: "highlight",  apiUrl: "/api/admin/Network/createStorageNetworkIpRange", method: "GET"]); }
    
    def deleteStorageNetworkIpRange() {  render(view: "deleteStorageNetworkIpRange", model: [menuRelease: "highlight",  apiUrl: "/api/admin/Network/deleteStorageNetworkIpRange", method: "GET"]); }
    
    def listStorageNetworkIpRange() {  render(view: "listStorageNetworkIpRange", model: [menuRelease: "highlight",  apiUrl: "/api/admin/Network/listStorageNetworkIpRange", method: "GET"]); }
    
    def updateStorageNetworkIpRange() {  render(view: "updateStorageNetworkIpRange", model: [menuRelease: "highlight",  apiUrl: "/api/admin/Network/updateStorageNetworkIpRange", method: "GET"]); }
    
    def listNiciraNvpDeviceNetworks() {  render(view: "listNiciraNvpDeviceNetworks", model: [menuRelease: "highlight",  apiUrl: "/api/admin/Network/listNiciraNvpDeviceNetworks", method: "GET"]); }
    
    def listNetworkIsolationMethods() {  render(view: "listNetworkIsolationMethods", model: [menuRelease: "highlight",  apiUrl: "/api/admin/Network/listNetworkIsolationMethods", method: "GET"]); }

}


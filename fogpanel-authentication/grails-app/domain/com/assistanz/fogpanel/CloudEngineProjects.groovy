package com.assistanz.fogpanel

import org.apache.commons.lang.builder.HashCodeBuilder
class CloudEngineProjects implements Serializable {
    Project project
    CloudEngine cloudEngine

    boolean equals(other) {
        if (!(other instanceof CloudEngineProjects)) {
            return false
        }

        other.project?.id == project?.id &&
        other.cloudEngine?.id == cloudEngine?.id
    }

    int hashCode() {
        def builder = new HashCodeBuilder()
        if (project) builder.append(project.id)
        if (cloudEngine) builder.append(cloudEngine.id)
        builder.toHashCode()
    }

    static CloudEngineProjects get(long projectId, long cloudEngineId) {
        find 'from CloudEngineProjects where project.id=:projectId and cloudEngine.id=:cloudEngineId',
        [projectId: projectId, cloudEngineId: cloudEngineId]
    }
	

    static CloudEngineProjects create(Project project, CloudEngine cloudEngine, boolean flush = false) {
        println(" Ldap project cloudEngine  create" )
        new CloudEngineProjects(project: project, cloudEngine: cloudEngine).save(flush: flush, insert: true)
    }

    static boolean remove(Project project, CloudEngine cloudEngine, boolean flush = false) {
        CloudEngineProjects instance = CloudEngineProjects.findByProjectAndCloudEngine(project, cloudEngine)
        if (!instance) {
            return false
        }

        instance.delete(flush: flush)
        true
    }

    static void removeAll(Project project) {
        executeUpdate 'DELETE FROM CloudEngineProjects WHERE project=:project', [project: project]
    }

    static void removeAll(CloudEngine cloudEngine) {
        executeUpdate 'DELETE FROM CloudEngineProjects WHERE cloudEngine=:cloudEngine', [cloudEngine: cloudEngine]
    }

    static mapping = {
        id composite: ['cloudEngine', 'project']
        version false
    }

    static constraints = {
    }
}

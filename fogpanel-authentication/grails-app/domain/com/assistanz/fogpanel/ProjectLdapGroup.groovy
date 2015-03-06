package com.assistanz.fogpanel

import org.apache.commons.lang.builder.HashCodeBuilder

class ProjectLdapGroup implements Serializable {
    Project project
    LdapGroup group

    boolean equals(other) {
        if (!(other instanceof ProjectLdapGroup)) {
            return false
        }

        other.project?.id == project?.id &&
        other.group?.id == group?.id
    }

    int hashCode() {
        def builder = new HashCodeBuilder()
        if (project) builder.append(project.id)
        if (group) builder.append(group.id)
        builder.toHashCode()
    }

    static ProjectLdapGroup get(long projectId, long groupId) {
        find 'from ProjectLdapGroup where project.id=:projectId and group.id=:groupId',
        [projectId: projectId, groupId: groupId]
    }
	

    static ProjectLdapGroup create(Project project, LdapGroup group, boolean flush = false) {
        println(" Ldap project group  create" )
        new ProjectLdapGroup(project: project, group: group).save(flush: flush, insert: true)
    }

    static boolean remove(Project project, LdapGroup group, boolean flush = false) {
        ProjectLdapGroup instance = ProjectLdapGroup.findByGroupAndProject(project, group)
        if (!instance) {
            return false
        }

        instance.delete(flush: flush)
        true
    }

    static void removeAll(Project project) {
        executeUpdate 'DELETE FROM ProjectLdapGroup WHERE project=:project', [project: project]
    }

    static void removeAll(LdapGroup group) {
        executeUpdate 'DELETE FROM ProjectLdapGroup WHERE group=:group', [group: group]
    }

    static mapping = {
        id composite: ['group', 'project']
        version false
    }

    static constraints = {
    }
}

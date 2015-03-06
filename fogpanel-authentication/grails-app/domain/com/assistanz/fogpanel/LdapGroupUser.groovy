package com.assistanz.fogpanel

import org.apache.commons.lang.builder.HashCodeBuilder

class LdapGroupUser implements Serializable {
    
    User user
    LdapGroup group

    boolean equals(other) {
        if (!(other instanceof LdapGroupUser)) {
            return false
        }

        other.user?.id == user?.id &&
        other.group?.id == group?.id
    }

    int hashCode() {
        def builder = new HashCodeBuilder()
        if (user) builder.append(user.id)
        if (group) builder.append(group.id)
        builder.toHashCode()
    }

    static LdapGroupUser get(long userId, long groupId) {
        find 'from LdapGroupUser where user.id=:userId and group.id=:groupId',
        [userId: userId, groupId: groupId]
    }
	

    static LdapGroupUser create(User user, LdapGroup group, boolean flush = false) {
        println(" Ldap user group  create" )
        new LdapGroupUser(user: user, group: group).save(flush: flush, insert: true)
    }

    static boolean remove(User user, LdapGroup group, boolean flush = false) {
        LdapGroupUser instance = LdapGroupUser.findByGroupAndUser(user, group)
        if (!instance) {
            return false
        }

        instance.delete(flush: flush)
        true
    }

    static void removeAll(User user) {
        executeUpdate 'DELETE FROM LdapGroupUser WHERE user=:user', [user: user]
    }

    static void removeAll(LdapGroup group) {
        executeUpdate 'DELETE FROM LdapGroupUser WHERE group=:group', [group: group]
    }

    static mapping = {
        id composite: ['group', 'user']
        version false
    }

    static constraints = {
    }
}

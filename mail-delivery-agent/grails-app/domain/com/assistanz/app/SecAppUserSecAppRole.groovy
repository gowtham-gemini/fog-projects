package com.assistanz.app

import org.apache.commons.lang.builder.HashCodeBuilder

class SecAppUserSecAppRole implements Serializable {

	SecAppUser secAppUser
	SecAppRole secAppRole

	boolean equals(other) {
		if (!(other instanceof SecAppUserSecAppRole)) {
			return false
		}

		other.secAppUser?.id == secAppUser?.id &&
			other.secAppRole?.id == secAppRole?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (secAppUser) builder.append(secAppUser.id)
		if (secAppRole) builder.append(secAppRole.id)
		builder.toHashCode()
	}

	static SecAppUserSecAppRole get(long secAppUserId, long secAppRoleId) {
		find 'from SecAppUserSecAppRole where secAppUser.id=:secAppUserId and secAppRole.id=:secAppRoleId',
			[secAppUserId: secAppUserId, secAppRoleId: secAppRoleId]
	}

	static SecAppUserSecAppRole create(SecAppUser secAppUser, SecAppRole secAppRole, boolean flush = false) {
		new SecAppUserSecAppRole(secAppUser: secAppUser, secAppRole: secAppRole).save(flush: flush, insert: true)
	}

	static boolean remove(SecAppUser secAppUser, SecAppRole secAppRole, boolean flush = false) {
		SecAppUserSecAppRole instance = SecAppUserSecAppRole.findBySecAppUserAndSecAppRole(secAppUser, secAppRole)
		if (!instance) {
			return false
		}

		instance.delete(flush: flush)
		true
	}

	static void removeAll(SecAppUser secAppUser) {
		executeUpdate 'DELETE FROM SecAppUserSecAppRole WHERE secAppUser=:secAppUser', [secAppUser: secAppUser]
	}

	static void removeAll(SecAppRole secAppRole) {
		executeUpdate 'DELETE FROM SecAppUserSecAppRole WHERE secAppRole=:secAppRole', [secAppRole: secAppRole]
	}

	static mapping = {
		id composite: ['secAppRole', 'secAppUser']
		version false
	}
}

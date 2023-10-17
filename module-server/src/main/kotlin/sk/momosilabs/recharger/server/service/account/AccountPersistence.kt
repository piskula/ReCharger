package sk.momosilabs.recharger.server.service.account

import sk.momosilabs.recharger.server.service.account.currentUser.CurrentGoogleUser

interface AccountPersistence {

    fun create(user: CurrentGoogleUser): Long

    fun existsBy(provider: String, identifierWithinProvider: String): Boolean

}

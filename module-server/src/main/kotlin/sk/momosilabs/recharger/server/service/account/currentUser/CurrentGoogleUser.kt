package sk.momosilabs.recharger.server.service.account.currentUser

data class CurrentGoogleUser(
    override val email: String,
    override val identifier: String,
    val provider: String,
) : CurrentUser

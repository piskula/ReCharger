package sk.momosilabs.recharger.server.config

import org.springframework.context.event.EventListener
import org.springframework.security.authentication.event.AuthenticationSuccessEvent
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.stereotype.Component
import sk.momosilabs.recharger.server.service.account.AccountPersistence
import sk.momosilabs.recharger.server.service.account.currentUser.CurrentGoogleUser
import sk.momosilabs.recharger.server.service.account.currentUser.CurrentUserService.Companion.tokenToUser

@Component
open class AuthenticationEvent(
    private val accountPersistence: AccountPersistence,
) {

    @EventListener
    fun onAuthenticationSuccess(successEvent: AuthenticationSuccessEvent) {
        val source = successEvent.source

        if (source is JwtAuthenticationToken) {
            val user = source.tokenToUser()

            if (user is CurrentGoogleUser) {
                if (!accountPersistence.existsBy(provider = user.provider, identifierWithinProvider = user.identifier)) {
                    accountPersistence.create(user)
                }
            }
        }
    }

}

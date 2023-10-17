package sk.momosilabs.recharger.server.service.account.currentUser

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.stereotype.Service

@Service
open class CurrentUserService {

    companion object {
        fun JwtAuthenticationToken.tokenToUser(): CurrentUser {
            val jwt = principal as Jwt

            val email = jwt.claims["email"] as String
            val sub = jwt.claims["sub"] as String
            val iss = jwt.claims["iss"] as String

            return CurrentGoogleUser(
                email = email,
                identifier = sub,
                provider = iss,
            )
        }
    }

    fun getCurrentUser(): CurrentUser =
        (SecurityContextHolder.getContext().authentication as JwtAuthenticationToken).tokenToUser()

    fun identifier(): String = getCurrentUser().identifier

}

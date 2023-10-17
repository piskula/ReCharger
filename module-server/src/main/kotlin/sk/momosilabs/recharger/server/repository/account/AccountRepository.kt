package sk.momosilabs.recharger.server.repository.account

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import sk.momosilabs.recharger.server.entity.account.AccountEntity

@Repository
interface AccountRepository : JpaRepository<AccountEntity, Long> {

    fun existsByProviderAndProviderIdentifier(provider: String, providerIdentifier: String): Boolean

//    fun findByProviderIdentifier(providerIdentifier: String): AccountEntity

}

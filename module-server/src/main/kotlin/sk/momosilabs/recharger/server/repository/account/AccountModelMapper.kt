package sk.momosilabs.recharger.server.repository.account

import sk.momosilabs.recharger.server.entity.account.AccountEntity
import sk.momosilabs.recharger.server.service.account.currentUser.CurrentGoogleUser

fun CurrentGoogleUser.toEntity() = AccountEntity(
    provider = provider,
    providerIdentifier = identifier,
    email = email,
)

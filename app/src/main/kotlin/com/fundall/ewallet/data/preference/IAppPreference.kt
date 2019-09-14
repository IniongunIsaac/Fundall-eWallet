package com.fundall.ewallet.data.preference

import com.fundall.ewallet.data.models.User
import com.fundall.ewallet.data.models.UserData

/**
 * Created by Isaac Iniongun on 2019-09-14.
 * For Fundall eWallet project.
 */

interface IAppPreference {

    var authorizationToken: String

    var userDetails: User

    var userDataDetails: UserData

    var hasSavedUserDetails: Boolean

    var hasSavedUserDataDetails: Boolean
}
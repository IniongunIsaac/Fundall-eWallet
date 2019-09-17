package com.fundall.ewallet.exceptions

import java.io.IOException

/**
 * Created by Isaac Iniongun on 2019-09-15.
 * For Fundall eWallet project.
 */

class NoConnectivityException(message: String = "No internet connection!"): IOException(message)
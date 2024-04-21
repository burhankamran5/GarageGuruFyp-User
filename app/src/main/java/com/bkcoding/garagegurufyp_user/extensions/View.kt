package com.bkcoding.garagegurufyp_user.extensions

import io.github.rupinderjeet.kprogresshud.KProgressHUD

fun KProgressHUD.isVisible(isLoading: Boolean) = if (isLoading) this.show() else this.dismiss()
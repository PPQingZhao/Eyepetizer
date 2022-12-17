package com.pp.library_ui.utils

import android.view.View
import com.pp.library_ui.R

object ViewTreeAppThemeViewModel {
    operator fun set(view: View, theme: AppTheme?) {
        view.setTag(R.id.view_tree_app_theme_view_model, theme)
    }

    operator fun get(view: View): AppTheme? {
        var found = view.getTag(R.id.view_tree_app_theme_view_model)
        if (found != null) return found as AppTheme
        var parent = view.parent
        while (found == null && parent is View) {
            val parentView = parent as View
            found = parentView.getTag(R.id.view_tree_app_theme_view_model)
            parent = parentView.parent
        }
        return if (found is AppTheme) found else null
    }
}
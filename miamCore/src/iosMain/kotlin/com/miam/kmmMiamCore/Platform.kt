package com.miam.kmmMiamCore

/**
 * This file set general platform (Android/Ios) settings
 * Ios implementation
 */

open abstract class PreferencesContext

class MiamPreferencesContext() : PreferencesContext()

actual typealias  KMMContext = PreferencesContext
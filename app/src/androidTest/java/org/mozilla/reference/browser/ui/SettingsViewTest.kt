/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.reference.browser.ui

import androidx.test.rule.GrantPermissionRule
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.mozilla.reference.browser.helpers.BrowserActivityTestRule
import org.mozilla.reference.browser.helpers.TestHelper.scrollToElementByText
import org.mozilla.reference.browser.ui.robots.mDevice
import org.mozilla.reference.browser.ui.robots.navigationToolbar

/**
 *   Tests for verifying the settings view options exist as expected:
 * - Appears when the settings submenu is tapped
 * - Expected options are displayed as listed below
 */

class SettingsViewTest {
    /* ktlint-disable no-blank-line-before-rbrace */ // This imposes unrgeadable grouping.

    // Grant the app access to the camera so that we can test the Firefox Accounts QR code reader
    @Rule @JvmField
    val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(android.Manifest.permission.CAMERA)

    @get:Rule val browserActivityTestRule = BrowserActivityTestRule()
    // This test verifies settings view items are all in place
    @Test
    fun settingsItemsTest() {
        navigationToolbar {
        }.openThreeDotMenu {
        }.openSettings {
            mDevice.waitForIdle()
            verifySettingsRecyclerViewToExist()
            verifyNavigateUp()
            verifySyncSigninButton()
            verifySyncHistorySummary()
            verifySyncQrCodeButton()
            verifySyncQrSummary()
            verifyPrivacyButton()
            verifyPrivacySummary()
            verifyOpenLinksInApps()
            verifyMakeDefaultBrowserButton()
            verifyAutofillAppsButton()
            varifyAutofillAppsSummary()
            verifyDeveloperToolsHeading()
            verifyRemoteDebugging()
            verifyCustomAddonCollectionButton()
            scrollToElementByText("About Reference Browser")
            verifyMozillaHeading()
            verifyAboutReferenceBrowserButton()
        }
    }

    @Test
    fun openFXATest() {
        navigationToolbar {
        }.openThreeDotMenu {
        }.openSettings {
        }.openFXASignin {
            verifyFXAUrl()
        }
    }

    // openFXAQrCodeTest tests that we get to the camera
    // Additional tests are needed to verify that the QR code reader works
    @Ignore("Test instrumentation process is crashing, see: https://github.com/mozilla-mobile/reference-browser/issues/1502")
    @Test
    fun openFXAQrCodeTest() {
        navigationToolbar {
        }.openThreeDotMenu {
        }.openSettings {
        }.openFXAQrCode {
            mDevice.waitForIdle()
            verifyFxAQrCode()
            mDevice.pressBack()
        }

    }

    @Test
    fun privacySettingsItemsTest() {
        navigationToolbar {
        }.openThreeDotMenu {
        }.openSettings {
        }.openSettingsViewPrivacy {
            verifyPrivacyUpButton()
            verifyPrivacySettings()
            verifyTrackingProtectionHeading()
            verifyTPEnableInNormalBrowsing()
            verifyTPEnableinPrivateBrowsing()
            verifyDataChoicesHeading()
            verifyUseTelemetryToggle()
            verifyUseTelemetryToggle()
            verifyTelemetrySummary()
        }
    }

    @Test
    fun setDefaultBrowserTest() {
        navigationToolbar {
        }.openThreeDotMenu {
        }.openSettings {
        }.makeDefaultBrowser {
            verifyAndroidDefaultApps()
        }
    }

    @Test
    fun remoteDebuggingViaUSB() {
        navigationToolbar {
        }.openThreeDotMenu {
        }.openSettings {
            toggleRemoteDebuggingOn()
            toggleRemoteDebuggingOff()
            toggleRemoteDebuggingOn()
        }
    }

    @Test
    fun aboutReferenceBrowserTest() {
        navigationToolbar {
        }.openThreeDotMenu {
        }.openSettings {
            scrollToElementByText("About Reference Browser")
        }.openAboutReferenceBrowser {
            verifyAboutBrowser()
        }
    }
}

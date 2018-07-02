package com.example.trungnguyen.projectx.base.base_application

/**
 * Created by Trung Nguyen on 09-Jun-18.
 */
class FakeCrashLibrary {
    companion object {
        fun log(priority: Int, tag: String, message: String) {
            // TODO add log entry to circular buffer.
        }

        fun logWarning(t: Throwable) {
            // TODO report non-fatal warning.
        }

        fun logError(t: Throwable) {
            // TODO report non-fatal error.
        }
    }

    private constructor() {
        throw AssertionError("No instances.");
    }
}
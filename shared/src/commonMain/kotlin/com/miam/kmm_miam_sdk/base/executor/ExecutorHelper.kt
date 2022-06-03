package com.miam.kmm_miam_sdk.base.executor

import kotlinx.coroutines.Job

class ExecutorHelper {
    companion object {
        fun emptyJob(): Job {
            val job = Job()
            job.complete()
            return job
        }

        fun cancelRunningJob(job: Job?) {
            if (job == null) return

            if (!job.isActive) return

            job.cancel()
        }
    }
}
package com.miam.kmmMiamCore.base.executor

import kotlinx.coroutines.Job

public class ExecutorHelper {
    public companion object {
        public fun emptyJob(): Job {
            val job = Job()
            job.complete()
            return job
        }

        public fun cancelRunningJob(job: Job?) {
            if (job == null) return

            if (!job.isActive) return

            job.cancel()
        }
    }
}
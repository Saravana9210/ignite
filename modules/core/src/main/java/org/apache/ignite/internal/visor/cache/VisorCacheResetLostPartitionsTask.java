/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.internal.visor.cache;

import org.apache.ignite.internal.processors.task.GridInternal;
import org.apache.ignite.internal.util.typedef.internal.S;
import org.apache.ignite.internal.visor.VisorJob;
import org.apache.ignite.internal.visor.VisorOneNodeTask;

/**
 * Reset lost partitions for caches.
 */
@GridInternal
public class VisorCacheResetLostPartitionsTask extends VisorOneNodeTask<VisorCacheResetLostPartitionsTaskArg, Void> {
    /** */
    private static final long serialVersionUID = 0L;

    /** {@inheritDoc} */
    @Override protected VisorCacheResetLostPartitionsJob job(VisorCacheResetLostPartitionsTaskArg arg) {
        return new VisorCacheResetLostPartitionsJob(arg, debug);
    }

    /**
     * Job that reset lost partitions for caches.
     */
    private static class VisorCacheResetLostPartitionsJob extends VisorJob<VisorCacheResetLostPartitionsTaskArg, Void> {
        /** */
        private static final long serialVersionUID = 0L;

        /**
         * @param arg Object with list cache names to reset lost partitions.
         * @param debug Debug flag.
         */
        private VisorCacheResetLostPartitionsJob(VisorCacheResetLostPartitionsTaskArg arg, boolean debug) {
            super(arg, debug);
        }

        /** {@inheritDoc} */
        @Override protected Void run(VisorCacheResetLostPartitionsTaskArg arg) {
            ignite.resetLostPartitions(arg.getCacheNames());

            return null;
        }

        /** {@inheritDoc} */
        @Override public String toString() {
            return S.toString(VisorCacheResetLostPartitionsJob.class, this);
        }
    }
}

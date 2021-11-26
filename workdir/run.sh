#!/bin/bash

set -e

# all the libs we need we copy to the upload directory. They will automatically be uploaded and put on the classpath
# of the worker
mkdir -p upload
cp ../target/*.jar upload

sessionId=${1:-session/@it}
clientCount=12
memberCount=2
duration=5m

coordinator --clean

agent-ssh "sudo killall -9 java || true"

instanceJvmArgs="-Dhazelcast.config.schema.validation.enabled=false"
instanceJvmArgs="${instanceJvmArgs} -Xms4G -Xmx4G"
memberJvmArgs="${instanceJvmArgs} -Dhazelcast.initial.min.cluster.size=${memberCount}"

coordinator     --members ${memberCount} \
                --memberArgs "${memberJvmArgs}" \
                --clients ${clientCount} \
                --clientArgs "${instanceJvmArgs}" \
                --duration ${duration} \
                --sessionId "${sessionId}" \
                --dedicatedMemberMachines ${memberCount} \
                test.properties

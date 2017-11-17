#!/bin/bash
cd /usr/local/bin/kumuluzee/


if [[ -z "${CONFIG_ETCD_HOSTS_RESOLVE}" ]];
then
    printf 'No CONFIG_ETCD_HOSTS_RESOLVE set to resolve.'
else
    printf "Resolving CONFIG_ETCD_HOSTS_RESOLVE => ${CONFIG_ETCD_HOSTS_RESOLVE} ...\n"

    RESOLVED_CONFIG_ETCD_HOSTNAME=`getent hosts $CONFIG_ETCD_HOSTS_RESOLVE | awk '{ print \$1 }'`
    printf "Resolved to: ${RESOLVED_CONFIG_ETCD_HOSTNAME}\n"

    RESOLVED_CONFIG=`echo ${KUMULUZEE_CONFIG_ETCD_HOSTS} | sed  -e "s/${CONFIG_ETCD_HOSTS_RESOLVE}/${RESOLVED_CONFIG_ETCD_HOSTNAME}/g"`
    printf "Resolved address: ${RESOLVED_CONFIG}\n"

    export KUMULUZEE_CONFIG_ETCD_HOSTS="${RESOLVED_CONFIG}"
fi

if [[ -z "${DISCOVERY_ETCD_HOSTS_RESOLVE}" ]];
then
    printf 'No DISCOVERY_ETCD_HOSTS_RESOLVE set to resolve.\n'
else
    printf "Resolving DISCOVERY_ETCD_HOSTS_RESOLVE => ${DISCOVERY_ETCD_HOSTS_RESOLVE} ...\n"

    RESOLVED_DISCOVERY_ETCD_HOSTNAME=`getent hosts $DISCOVERY_ETCD_HOSTS_RESOLVE | awk '{ print \$1 }'`
    printf "Resolved to: ${RESOLVED_DISCOVERY_ETCD_HOSTNAME}\n"

    RESOLVED_DISCOVERY=`echo $KUMULUZEE_DISCOVERY_ETCD_HOSTS | sed  -e "s/${DISCOVERY_ETCD_HOSTS_RESOLVE}/${RESOLVED_DISCOVERY_ETCD_HOSTNAME}/g"`
    printf "Resolved address: ${RESOLVED_DISCOVERY}\n"

    export KUMULUZEE_DISCOVERY_ETCD_HOSTS="${RESOLVED_DISCOVERY}"
fi
########################################
### RUN JAR
########################################
if [[ -z "${JAR}" ]];
then
    printf 'You need to pass jar name.'
    exit 1
else
    printf 'Starting KumuluzEE .. \n'
    java -jar ${JAR}
fi

getent hosts $CONFIG_ETCD_HOSTS_RESOLVE | awk '{ print $1 }'
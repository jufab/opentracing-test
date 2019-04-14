#FROM docker-virtual-2.artefact-repo.pole-emploi.intra/pe/oracle/alpine-weblogic-domain:12.2.1.3-1.4
#FROM docker-virtual.artefact-repo.pole-emploi.intra/playniuniu/weblogic-domain:12.2.1.2
FROM docker-virtual.artefact-repo.pole-emploi.intra/gabikiwi/weblogic-1221-domain:latest
#FROM docker-virtual.artefact-repo.pole-emploi.intra/basking70/weblogic

USER oracle
COPY --chown=oracle:oracle patch/* /u01/oracle/
COPY --chown=oracle:oracle patch/27741413/ /u01/oracle/27741413/

RUN yes | /u01/oracle/OPatch/opatch apply /u01/oracle/23617388.zip
#&& \
#    yes | /u01/oracle/OPatch/opatch apply /u01/oracle/27741413
#RUN rm /u01/oracle/wlserver/common/deployable-libraries/jax-rs-2.0.war
#/u01/oracle/23617388.zip /u01/oracle/27741413.zip

#COPY ear/target/personne-adresse-api-sldng.ear  /u01/oracle/user_projects/domains/base_domain/autodeploy/
#ENV EAR_NAME="personne-adresse-api-sldng.ear" \
#    EAR_PKG_FILE="personne-adresse-api-sldng.ear" \
#    EAR_PKG_LOCATION="/u01/oracle"
COPY impl/target/personne-adresse-api-sldng.war /u01/oracle/user_projects/domains/base_domain/autodeploy/

CMD "startWebLogic.sh"

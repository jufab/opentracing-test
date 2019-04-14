===============================
Patch Set Update (PSU) for Bug: 27741413
===============================

Date:  Mon Jun 11 12:28:41 2018
---------------------------------
Platform Patch for : Generic
Product Patched : ORACLE WEBLOGIC SERVER
Product Version      : 12.2.1.2.0


This document describes how to install the interim patch for
bug #  27741413. It includes the following sections:

	Section 1, "Zero Downtime Patching"

 	Section 2, "Prerequisites"

	Section 3, "Pre-Installation Instructions"

	Section 4, "Installation Instructions"

	Section 5, "Post-Installation Instructions"

	Section 6, "Deinstallation Instructions"

	Section 7, "Post Deinstallation Instructions"

	Section 8, "Bugs Fixed by This Patch"

        Section 9, "Known Issues"


 
1 Zero Downtime Patching
------------------------------
This patch has been marked as eligible for Zero Downtime Patching. 
The type of Zero Downtime Patching supported by this patch is FMW_ROLLING_ORACLE_HOME.

With Zero Downtime Patching, a Patch can be applied to a system in a manner
that does not incur any downtime. This ensures that the system can remain
available and functioning during the patching process. Certain
pre-requisites, however, must be met before the patch can be applied.

For more information, consult the My Oracle Support MOS Note: 1942159.1 
 

2 Prerequisites
----------------
Ensure that you meet the following requirements before you install or
deinstall the patch:

1. Before applying the non-mandatory patches, ensure that you have the
exact symptoms described in the bug.


2. For users of Oracle JDKs and JVMs, we strongly recommend 
applying the latest Java Critical Patch Updates (CPUs) as soon as they are 
released.   Please refer to  Obtaining Java SE (JDK/JRE) for 
Oracle Fusion Middleware Products (Doc ID 1506916.1) for further information.
https://support.oracle.com/oip/faces/secure/km/DocumentDisplay.jspx?id=1506916.1



3. Oracle Fusion Middleware 12.2.1 products are installed with OPatch NextGen 13.3 to apply interim patches.

The OPatch utility should be updated over time to resolve known issues.

You can check your version using the following command:

ORACLE_HOME/OPatch/opatch version

Checking Patch 6880880, if a new version is available , download "OUI NextGen 13.3" for your platform.

Review the following for more information:
Doc ID 1587524.1, "Using OUI NextGen OPatch 13 for Oracle Fusion Middleware 12c (12.1.2+)"
https://support.oracle.com/rs?type=doc&id=1587524.1

For Oracle Fusion Middleware OPatch usage, refer to the URL below which
redirects to the latest FMW 12c document:
"Oracle Fusion Middleware Patching With OPatch"
 http://www.oracle.com/pls/topic/lookup?ctx=fmw122100&id=OPATC 


4. Verify the OUI Inventory.
OPatch needs access to a valid OUI inventory to apply patches.

Note: This needs the ORACLE_HOME to be set(refer section "2. Pre-Installation Instructions")
prior to run the below commands:

Validate the OUI inventory with the following commands:

$ opatch lsinventory -jre $ORACLE_HOME/oracle_common/jdk/jre

Note:
All OPatch commands should be run with -jre option.
Make sure the JDK version you use is the certified version for your product.

If the command errors out, contact Oracle Support and work to validate
and verify the inventory setup before proceeding.


5. Confirm the executables appear in your system PATH.

The patching process will use the unzip and the OPatch executables. After
setting the ORACLE_HOME environment, confirm if the following executables
exist, before proceeding to the next step:

- opatch
- unzip

If either of these executables do not show in the PATH, correct the
problem before proceeding.


6. Create a location for storing the unzipped patch. This location
will be referred to later in the document as PATCH_TOP.

NOTE: On WINDOWS, the preferred location is the drive root directory.
For example, "C:\PATCH_TOP" and avoid choosing locations like,
"C:\Documents and Settings\username\PATCH_TOP".
This is necessary due to the 256 characters limitation on windows
platform.

3 Pre-Installation Instructions
-------------------------------

1. Set the ORACLE_HOME environment variable to the directory where you have installed ORACLE WEBLOGIC SERVER.



4 Installation Instructions
---------------------------

1. Unzip the patch zip file into the PATCH_TOP.

$ unzip -d PATCH_TOP p27741413_122120_Generic.zip

NOTE: On WINDOWS, the unzip command has a limitation of 256 characters in the path name.
If you encounter this, please use an alternate ZIP utility like 7-Zip to unzip the patch.

For example: To unzip using 7-zip, run the command:
"c:\Program Files\7-Zip\7z.exe" x  p27741413_122120_Generic.zip

2. Set your current directory to the directory where the patch is located.

$ cd PATCH_TOP/27741413

3. Run OPatch to apply the patch.

$ opatch apply

Note:
-----
When OPatch starts, it validates the patch and makes sure that there are no
conflicts with the software already installed in the ORACLE_HOME.

In case of opatch conflict, you will see a warning message similar to the one mentioned below:

Interim Patch XXXX has Conflict with patch(es) [ YYYY ] in OH ...
Conflict patches: YYYY
Patch(es) YYYY conflict with the patch currently being installed (XXXX).
If you continue, patch(es) YYYY will be rolled back and the new patch (XXXX) will be installed.

If a merge of the new patch (XXXX) and the conflicting patch(es) ( YYYY) is required,contact Oracle Support Services and request a Merged patch.

Do you want to proceed? [y|n]
n

You must stop the patch installation and contact oracle support on how to proceed.

5 Post-Installation Instructions
---------------------------------

Note:   The fix for bug 26929163 updates the runtime components for the 
WLS plugin for the RCU and Upgrade Assistant tools.  It will prevent any 
future WLS schema installs or upgrades from allowing WLS schema owners to 
retain ANY privs once installation or upgrade have been completed.  The 
patch will *not* affect schemas that have already been installed or 
upgraded to the correct schema version.

For existing installations that wish to remove the ANY privileges that 
have been assigned to the WLS schema owners, users can run the script 
located at $MW_HOME/oracle_common/common/sql/wlsservices/sql/cleanup.sql
as the DBA user and provide the WLS schema owner names.  For example, if 
the WLS schema owners are DEV1_WLS and DEV1_WLS_RUNTIME:

      sqlplus <dba-connect-info> cleanup.sql  DEV1_WLS  DEV1_WLS_RUNTIME


6 Deinstallation Instructions
------------------------------

If you experience any problems after installing this patch, remove the patch as
follows:

1. Make sure to follow the same Prerequisites or pre-install steps (if any)
when deinstalling a patch.
This includes setting up any environment variables like ORACLE_HOME and
verifying the OUI inventory before deinstalling.

2. Change to the directory where the patch was unzipped.

$ cd PATCH_TOP/27741413

3. Run OPatch to deinstall the patch.

$ opatch rollback -id  27741413

7 Post Deinstallation Instructions
-----------------------------------
Restart all servers (AdminServer and all Managed server(s)).

This is necessary to redeploy the original applications and bring the
environment back to it's original state.

8 Bugs Fixed by This Patch
--------------------------
For a list of Bugs Fixed in this PSU, refer to the corresponding Bugs 
Fixed Document listed in MOS Doc ID 1470197.1 


9 Known Issues
------------
For information about OPatch issues, see My Oracle Support Document 293369.1
Master Note for OPatch

For issues documented after the release of this ORACLE WEBLOGIC SERVER Patch Set Update, see My Oracle Support Document <2137518.1>  <(Known Issues for Oracle WebLogic Server (OWLS) 12.2.1.0.X Patch Set Updates)>

If you are running with a security manager and experience
java.io.SerializablePermission "serialFilter" permission exceptions, then you
will need to update the weblogic policy file to include the following line:

    permission java.io.SerializablePermission "serialFilter";

in the coherence.jar section of the weblogic policy file:

   grant codeBase "file:@WL_HOME/../coherence/lib/coherence.jar" {

Note:  This patch was built with a standard FMW patch builder.  When it is 
installed in a WLS-only (e.g. non-FMW) environment you may see the following 
message appear: 

ApplySession: Optional component(s) [ oracle.webservices.jrf, 12.2.1.2.0 ]   
not present in the Oracle Home or a higher version is found. 

If you see this message it can be safely ignored.


-----------------------------------------------------------------------------
DISCLAIMER:

This interim patch has only undergone basic unit testing, and has not been
through a complete test cycle generally followed for a production patch set.
Though the fixes in this document rectifies the bug, Oracle Corporation will
not be responsible for other issues that may arise due to these fixes.
Oracle Corporation recommends to later upgrade to the next production patch
set, when available. Applying this patch could overwrite other interim
patches applied since the last patch set. Customers need to make a request
to Oracle Support for a patch that includes those fixes, as well as inform
Oracle Support about all the patches installed when a Service Request is
opened. Please download, test, and provide feedback as soon as possible
to assist in the timely resolution of this problem.

Copyright (c)  2018, Oracle and/or its affiliates. All rights reserved.
----------------------------------------------------------------------------- 

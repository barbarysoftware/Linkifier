# Maven publishing guide for linkify-java

This is for internal use only.

When ready to publish a repo, do this:

> mvn clean deploy -Possrh

This deploys to Sonatype's snapshot/staging repos.

If the version ends with -SNAPSHOT, the artifact will be deployed to the snapshots repository. If it doesn't, it will instead be uploaded to the staging repository.

## Release Process

Once a non-artifact is on Sonatype, do the following:

1. Login to the Sonatype Repository Manager with your Sonatype credentials https://s01.oss.sonatype.org/
2. Move to the “Staging Repositories” section on the left. 
3. Choose the staging repository to promote. 
4. “Close” the staging repository. 
5. “Release” the staging repository.

# Maven publishing guide for linkify-java

This is for internal use only.

## Automated steps

1. In pom.xml, update the version number in the &lt;version&gt; tag. Usually you'll increase the minor version by one.
2. Commit this change with commit message `Prepare release X.Y.ZZ`, where X.Y.ZZ is the new version number
3. Push to GitHub
4. In the project's [GitHub Releases page](https://github.com/barbarysoftware/Linkifier/releases), draft a new release.
5. In "Choose a tag", tag the release with `vX.Y.ZZ`, where X, Y, and ZZ are the new version numbers. eg `v0.1.99`
6. Release title should be `Release X.Y.ZZ`, eg `Release 0.1.99`
7. Click on the **Publish Release** button
8. A [GitHub action](https://github.com/barbarysoftware/Linkifier/actions) will be automatically triggered to publish the new release on Maven Central

It can take a while (30 minutes or more) for the release to be publicly viewable.


## Manual steps (for use when automated approach is broken or stuck)



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

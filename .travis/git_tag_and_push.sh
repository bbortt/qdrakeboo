#!/usr/bin/env bash
# Tag and push image from Travis CI build.
#
# Usage: `./tag_and_push`
#
# Requires the following environment variables:
#   $GITHUB_USERNAME      The username used to tag the commit
#   $GITHUB_EMAIL         The email used to tag the commit
#   $GITHUB_TOKEN         The GitHub authentication token.
#   $TRAVIS_BUILD_NUMBER  The build number of

git config --local user.name $GITHUB_USERNAME
git config --local user.email $GITHUB_EMAIL

git tag $TRAVIS_BUILD_NUMBER

git remote add origin https://${GITHUB_TOKEN}@github.com/bbortt/qdrakeboo.git > /dev/null 2>&1
git push --tags --quiet -u origin master

#!/usr/bin/env bash
# Tag and push image from Travis CI build.
#
# Usage: `./tag_and_push`
#
# Requires the following environment variables:
#   $GITHUB_TOKEN         The GitHub authentication token.
#   $TRAVIS_BUILD_NUMBER  The build number of

set -ex

git config --local user.name "Travis CI"
git config --local user.email "builds@travis-ci.com"

git tag $TRAVIS_BUILD_NUMBER

git push --quiet https://${GITHUB_TOKEN}@github.com/bbortt/qdrakeboo.git $TRAVIS_BUILD_NUMBER

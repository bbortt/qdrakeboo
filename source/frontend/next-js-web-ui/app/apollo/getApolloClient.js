// @flow
import getConfig from 'next/config'

import { ApolloClient } from 'apollo-client'
import { HttpLink } from 'apollo-link-http'
import { InMemoryCache } from 'apollo-cache-inmemory'

import fetch from 'isomorphic-unfetch'

const { publicRuntimeConfig } = getConfig()

let apolloClient = null

// Polyfill fetch() on the server (used by apollo-client)
if (typeof window === 'undefined') {
  global.fetch = fetch
}

const create = () => {
  const isBrowser = typeof window !== 'undefined'

  const httpLinkConfig = {
    uri: isBrowser
      ? `${publicRuntimeConfig.publicUrl}/api`
      : `${publicRuntimeConfig.apiUrl}/graphql`,
    credentials: 'same-origin', // TODO: Additional fetch() options like `credentials` or `headers`
    headers: {},
  }

  if (isBrowser) {
    httpLinkConfig.headers[
      publicRuntimeConfig.apiForwardToHeaderName
    ] = `${publicRuntimeConfig.apiUrl}/graphql`
  }

  const apolloConfig = {
    connectToDevTools: isBrowser,
    ssrMode: !isBrowser,
    link: new HttpLink(httpLinkConfig),
    cache: new InMemoryCache().restore({}),
  }

  return new ApolloClient(apolloConfig)
}

export default () => {
  if (typeof window === 'undefined') {
    return create()
  }

  if (!apolloClient) {
    apolloClient = create()
  }

  return apolloClient
}

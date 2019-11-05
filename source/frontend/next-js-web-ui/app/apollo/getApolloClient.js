// @flow
import { ApolloClient } from 'apollo-client'
import { HttpLink } from 'apollo-link-http'
import { InMemoryCache } from 'apollo-cache-inmemory'

import { syncWellKnown } from '../util/loadWellKnown'

const create = (): ApolloClient => {
  const httpLinkConfig = {
    uri: syncWellKnown().api.url,
    // credentials: 'same-origin', // TODO: Additional fetch() options like `credentials` or `headers`
    headers: {},
  }

  const isDev = process.env.NODE_ENV === 'development'

  const apolloConfig = {
    connectToDevTools: isDev,
    ssrMode: false,
    link: new HttpLink(httpLinkConfig),
    cache: new InMemoryCache().restore({}),
  }

  return new ApolloClient(apolloConfig)
}

let apolloClient = null

export default (): ApolloClient => {
  if (typeof window === 'undefined') {
    return create()
  }

  if (!apolloClient) {
    apolloClient = create()
  }

  return Promise.resolve(apolloClient)
}

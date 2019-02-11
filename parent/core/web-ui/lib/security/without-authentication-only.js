import React from 'react';

const authenticatedEntryPoint = '/home'

export default function withoutAuthenticationOnly(Component: React.Component): React.Component {
  return class AnonymousPage extends React.Component {
    static displayName = `withoutAuthenticationOnly(${Component.displayName
    || Component.name
    || 'AnonymousPage'})`

    static async getInitialProps({ctx}) {
      const {isServer, req, res} = ctx

      if (req.session.token && res) {
        res.redirect(authenticatedEntryPoint)
      }

      let pageProps = {}
      if (Component.getInitialProps) {
        pageProps = await Component.getInitialProps(props)
      }

      return {isServer, ...pageProps}
    }

    render() {
      return <Component {...this.props} />
    }
  }
}

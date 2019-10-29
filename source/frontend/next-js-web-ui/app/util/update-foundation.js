// @flow
let initialized = false

export default () => {
  if (typeof window !== 'undefined') {
    if (!initialized) {
      require('foundation-sites')
      initialized = !initialized
    }

    // $FlowFixMe
    $(document).foundation()
  }
}

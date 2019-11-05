// @flow
let loaded = false
const initialized = []

export default (selector: ?string) => {
  if (typeof window !== 'undefined') {
    if (!loaded) {
      require('foundation-sites')
      // $FlowFixMe
      $(document).foundation()
      loaded = !loaded
    }

    if (selector && !initialized.includes(selector)) {
      // $FlowFixMe
      $(selector).foundation()
      initialized.push(selector)
    }
  }
}

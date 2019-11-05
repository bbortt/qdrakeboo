// @flow
let loaded = false
const initialized = []

export default (selector: string | null) => {
  if (typeof window !== 'undefined') {
    if (!loaded) {
      require('foundation-sites')
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

import Enzyme from 'enzyme'
import Adapter from 'enzyme-adapter-react-16'

import cssModulesRequireHook from 'css-modules-require-hook'
import sass from 'node-sass'

cssModulesRequireHook({
  extensions: ['.scss'],
  preprocessCss: data => sass.renderSync({data}).css
})

Enzyme.configure({adapter: new Adapter()})

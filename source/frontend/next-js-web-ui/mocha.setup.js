import path from 'path'

import Enzyme from 'enzyme'
import Adapter from 'enzyme-adapter-react-16'

import cssModulesRequireHook from 'css-modules-require-hook'
import sass from 'node-sass'
import nodeSassPackageImporter from 'node-sass-package-importer'

cssModulesRequireHook({
  extensions: ['.scss'],
  preprocessCss: (data, filepath) =>
    sass.renderSync({
      data,
      includePaths: [path.resolve(filepath, '..')],
      importer: nodeSassPackageImporter(),
    }).css,
})

Enzyme.configure({ adapter: new Adapter() })

import React from 'react'
import {connect} from 'react-redux'

require('./footer.scss')

class Footer extends React.Component {

  render() {
    return (
      <footer>
        <p>
          Made with &hearts; | Developed on <a href='https://github.com/bbortt/my-little-streaming-dream'>GitHub</a>
        </p>
      </footer>
    )
  }
}

export default connect()(Footer)

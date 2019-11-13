// @flow
import React from 'react'

import { Store, Unsubscribe } from 'redux'
import debounce from 'lodash/debounce'

import { withLoginRequired } from 'use-auth0-hooks'

import subscribeToReduxStore from '../../../app/util/redux/subscribeToReduxStore'
import withReduxContext from '../../../app/util/redux/withReduxContext'

import { resetPassword } from '../../../app/state/action'

import updateFoundation from '../../../app/util/updateFoundation'

import AccountContainer from '../../../app/components/account/AccountContainer'
import type { ReduxState } from '../../../app/state/reducer'

require('./reset-password.scss')

type ResetPasswordProps = {
  store: {
    dispatch: (action: any) => void,
  },
  remote: {
    loading: boolean,
    error: string,
  },
}

type ResetPasswordState = {
  password: string,
  confirmation: string,
  formErrors: Array<string>,
  submitDisabled: boolean,
  unsubscribe: Unsubscribe,
}

export class ResetPasswordClass extends React.Component<
  ResetPasswordProps,
  ResetPasswordState
> {
  constructor(props: ResetPasswordProps) {
    super(props)

    const { store } = props

    this.state = {
      password: '',
      confirmation: '',
      formErrors: [],
      submitDisabled: true,
      unsubscribe: this.subscribe(store),
    }

    this.handleChange = this.handleChange.bind(this)
    this.checkFormErrors = debounce(this.checkFormErrors.bind(this), 500)
    this.handleSubmit = this.handleSubmit.bind(this)
  }

  componentDidMount = () => {
    updateFoundation('#reset-password')
  }

  componentWillUnmount = () => {
    const { unsubscribe } = this.state
    unsubscribe()
  }

  subscribe = (store: Store<ReduxState>) => {
    return subscribeToReduxStore(
      store,
      reduxState => reduxState.userManagement.resetPassword,
      this.handleStateChange(this)
    )
  }

  handleStateChange = (reference: ResetPasswordClass) => {
    return (resetPasswordState: { loading: boolean, error: string }) => {
      const { loading, error } = resetPasswordState

      if (!loading) {
        if (error) {
          const { formErrors } = reference.state
          formErrors.push(error)
          reference.setState({ formErrors })
        } else {
          reference.setState({
            password: '',
            confirmation: '',
            formErrors: [],
            submitDisabled: true,
          })
        }

        reference.checkFormErrors()
      }
    }
  }

  handleChange = (event: SyntheticInputEvent<HTMLInputElement>) => {
    const { target } = event
    this.setState({ [target.name]: target.value }, this.checkFormErrors())
  }

  checkFormErrors = () => {
    const { password, confirmation } = this.state
    const formErrors = []

    if (password !== '' && password.length < 8) {
      formErrors.push('Password must be at least 8 characters in length!')
    }

    if (confirmation !== '' && password !== confirmation) {
      formErrors.push('Password and confirmation do not match!')
    }

    const submitDisabled =
      password === '' || confirmation === '' || formErrors.length !== 0

    this.setState({ formErrors, submitDisabled })
  }

  handleSubmit = (event: SyntheticEvent<HTMLButtonElement>) => {
    event.preventDefault()

    if (this.hasErrors()) {
      return
    }

    const { store } = this.props
    const { password, confirmation } = this.state

    store.dispatch(resetPassword(password, confirmation))
  }

  hasErrors = () => {
    const { formErrors } = this.state
    return formErrors.length !== 0
  }

  render() {
    const { password, confirmation, formErrors, submitDisabled } = this.state

    return (
      <AccountContainer>
        <div id="reset-password">
          <h2>Reset Password</h2>

          <form onSubmit={this.handleSubmit}>
            <div className="grid-container">
              <div className="grid-x grid-padding-x">
                <div className="medium-6 cell">
                  <label htmlFor="password">
                    {' '}
                    New Password
                    <input
                      id="password"
                      name="password"
                      type="password"
                      placeholder="New Password"
                      value={password}
                      onChange={this.handleChange}
                    />
                  </label>
                </div>
                <div className="medium-6 cell">
                  <label htmlFor="confirmation">
                    {' '}
                    Confirm New Password
                    <input
                      id="confirmation"
                      name="confirmation"
                      type="password"
                      placeholder="Confirm Password"
                      value={confirmation}
                      onChange={this.handleChange}
                    />
                  </label>
                </div>
                <div className="medium-10 cell">
                  <span className="form-error" hidden={!this.hasErrors()}>
                    {formErrors[0]}
                  </span>
                </div>
                <div className="medium-2 cell">
                  <div className="input-group-button float-right">
                    <input
                      id="button-submit"
                      type="submit"
                      className="button"
                      disabled={submitDisabled}
                      value="Submit"
                    />
                  </div>
                </div>
              </div>
            </div>
          </form>
        </div>
      </AccountContainer>
    )
  }
}

export default withLoginRequired(withReduxContext(ResetPasswordClass))

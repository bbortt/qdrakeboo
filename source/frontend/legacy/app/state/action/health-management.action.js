// @flow
export const BACKEND_UNREACHABLE: string =
  'Health-Management: Backend unreachble'

export type BackendUnreachableAction = {
  type: string,
}

export type HealthManagementAction = BackendUnreachableAction

export const backendUnreachable = (): BackendUnreachableAction => {
  return { type: BACKEND_UNREACHABLE }
}

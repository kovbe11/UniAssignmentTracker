import decode from 'jwt-decode';

export class User {
  username: string;
  token: string;
  roles: string[];
}

export function jwtToUser(jwt: string): User {
  const tokenPayload = decode(jwt);

  return {
    // @ts-ignore
    username: tokenPayload.username,
    // @ts-ignore
    roles: tokenPayload.roles,
    token: jwt,
  };
}

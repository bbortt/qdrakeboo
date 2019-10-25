import React from 'react';

import {useRouter} from 'next/router';

export const OopsClass = () => {
  const router = useRouter();
  const {message} = router.query;

  return (
      <div>
        <h1>Oops</h1>
        <p>
          An error occured when signing in!
        </p>
        <pre>
        {message || 'Unknown Error'}
      </pre>
      </div>
  );
}

export default OopsClass

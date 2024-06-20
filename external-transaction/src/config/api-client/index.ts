const baseUrl = process.env.GATEWAY_BASE_URL;
const credentials = 'include';

export interface options {
  method?: string;
  body?: any;
  headers?: any;
  params?: any;
  url?: any;
  data?: any;
  json?: any;
  timeout?: any;
}

const webClient = async (endpoint: any, options: options) => {
  const url = `${baseUrl}${endpoint}`;

  const defaultOptions = {
    credentials,
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const mergedOptions = {
    ...defaultOptions,
    ...options,
    headers: {
      ...defaultOptions.headers,
      ...options.headers,
    },
  };

  let response = await fetch(url, {
    method: mergedOptions.method,
    body: JSON.stringify(mergedOptions.body),
    headers: mergedOptions.headers,
  });
  if (!response.ok) {
    return Promise.reject(new Error(`HTTP error! status: ${response.status}`));
  }
  return response.json();
};

export default webClient;

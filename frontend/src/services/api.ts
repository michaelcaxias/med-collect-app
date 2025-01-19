import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api'
});

export interface CreateUserRequest {
  firebaseUid: string;
  name: string;
}

export const createUser = async (data: CreateUserRequest) => {
  const response = await api.post('/users', data);
  return response.data;
};

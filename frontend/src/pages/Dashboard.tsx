import { useAuth } from '../contexts/AuthContext';
import { EmailVerificationAlert } from '../components/alerts/EmailVerificationAlert';

export const Dashboard = () => {
  const { currentUser } = useAuth();

  return (
    <div className="min-h-screen bg-gray-100">
      <EmailVerificationAlert />
      
      <div className="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
        <div className="px-4 py-6 sm:px-0">
          <div className="border-4 border-dashed border-gray-200 rounded-lg h-96 p-4">
            <h1 className="text-2xl font-semibold text-gray-900">
              Bem-vindo ao Dashboard
            </h1>
            <p className="mt-2 text-gray-600">
              {currentUser?.email}
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};

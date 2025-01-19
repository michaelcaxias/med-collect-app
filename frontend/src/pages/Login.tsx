import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import { AuthLayout } from '../components/layouts/AuthLayout';
import { FormInput } from '../components/forms/FormInput';
import { SubmitButton } from '../components/forms/SubmitButton';
import { ErrorMessage } from '../components/forms/ErrorMessage';

export const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const { signIn } = useAuth();
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError('');
    
    try {
      setLoading(true);
      await signIn(email, password);
      navigate('/dashboard');
    } catch (error) {
      setError('Erro ao fazer login. Verifique suas credenciais e tente novamente.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <AuthLayout
      title="Bem-vindo(a)"
      subtitle="Faça login para acessar o sistema"
      heroText="Gerencie suas coletas médicas de forma simples e eficiente"
    >
      <form onSubmit={handleSubmit} className="space-y-6">
        <div className="space-y-5">
          <FormInput
            id="email"
            label="Email"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            placeholder="seu@email.com"
            required
          />

          <FormInput
            id="password"
            label="Senha"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="••••••••"
            required
          />
        </div>

        {error && (
          <ErrorMessage message={error} />
        )}

        <SubmitButton
          loading={loading}
          loadingText="Entrando..."
          text="Entrar"
        />

        <div className="text-center">
          <p className="text-sm text-gray-600">
            Não tem uma conta?{' '}
            <Link to="/register" className="text-indigo-600 hover:text-indigo-700 font-medium">
              Cadastre-se
            </Link>
          </p>
        </div>
      </form>
    </AuthLayout>
  );
};

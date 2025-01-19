import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import { AuthLayout } from '../components/layouts/AuthLayout';
import { FormInput } from '../components/forms/FormInput';
import { SubmitButton } from '../components/forms/SubmitButton';
import { ErrorMessage } from '../components/forms/ErrorMessage';

export const Register = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const { signUp } = useAuth();
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError('');

    if (!name.trim()) {
      setError('O nome é obrigatório');
      return;
    }

    if (password !== confirmPassword) {
      setError('As senhas não coincidem');
      return;
    }

    if (password.length < 6) {
      setError('A senha deve ter pelo menos 6 caracteres');
      return;
    }
    
    try {
      setLoading(true);
      console.log('Iniciando registro com:', { email, name });
      await signUp(email, password, name);
      console.log('Registro concluído com sucesso');
      navigate('/dashboard');
    } catch (error: any) {
      console.error('Erro detalhado:', error);
      if (error.response) {
        console.error('Resposta da API:', error.response.data);
      }
      setError('Erro ao criar conta. Verifique suas informações e tente novamente.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <AuthLayout
      title="Criar conta"
      subtitle="Preencha os dados abaixo para se cadastrar"
      heroText="Junte-se a nós e comece a gerenciar suas coletas médicas de forma simples e eficiente"
    >
      <form onSubmit={handleSubmit} className="space-y-6">
        <div className="space-y-5">
          <FormInput
            id="name"
            label="Nome completo"
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Seu nome completo"
            required
          />

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

          <FormInput
            id="confirmPassword"
            label="Confirmar senha"
            type="password"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            placeholder="••••••••"
            required
          />
        </div>

        <ErrorMessage message={error} />

        <SubmitButton
          loading={loading}
          loadingText="Criando conta..."
          text="Criar conta"
        />

        <div className="text-center">
          <span className="text-sm text-gray-600">
            Já tem uma conta?{' '}
            <Link to="/login" className="font-medium text-indigo-600 hover:text-indigo-500">
              Faça login
            </Link>
          </span>
        </div>
      </form>
    </AuthLayout>
  );
};

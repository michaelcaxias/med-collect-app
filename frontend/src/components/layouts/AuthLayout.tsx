interface AuthLayoutProps {
  children: React.ReactNode;
  title: string;
  subtitle: string;
  heroText: string;
}

export const AuthLayout = ({ children, title, subtitle, heroText }: AuthLayoutProps) => {
  return (
    <div className="min-h-screen flex flex-col md:flex-row">
      {/* Lado esquerdo - Área decorativa */}
      <div className="hidden md:flex md:w-1/2 bg-gradient-to-br from-indigo-600 to-blue-500 p-12 text-white justify-center items-center">
        <div className="max-w-xl">
          <h1 className="text-4xl font-bold mb-4">MedCollect</h1>
          <p className="text-xl text-indigo-100">
            {heroText}
          </p>
        </div>
      </div>

      {/* Lado direito - Formulário */}
      <div className="flex-1 flex items-center justify-center p-4 bg-gray-50">
        <div className="w-full max-w-md">
          <div className="bg-white rounded-xl shadow-lg p-8 space-y-6">
            <div className="text-center md:text-left">
              <h2 className="text-3xl font-bold text-gray-900 md:hidden">MedCollect</h2>
              <h3 className="text-2xl font-semibold text-gray-900 mb-1">{title}</h3>
              <p className="text-gray-600">
                {subtitle}
              </p>
            </div>
            {children}
          </div>
        </div>
      </div>
    </div>
  );
};

interface SubmitButtonProps {
  loading: boolean;
  loadingText: string;
  text: string;
}

export const SubmitButton = ({ loading, loadingText, text }: SubmitButtonProps) => {
  return (
    <button
      type="submit"
      disabled={loading}
      className={`
        w-full py-3 px-4 rounded-lg text-white font-medium text-sm
        transition-all duration-150 ease-in-out
        ${loading 
          ? 'bg-indigo-400 cursor-not-allowed' 
          : 'bg-indigo-600 hover:bg-indigo-700 hover:shadow-md focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500'
        }
        flex justify-center items-center
      `}
    >
      {loading && (
        <svg 
          className="animate-spin -ml-1 mr-3 h-5 w-5 text-white" 
          xmlns="http://www.w3.org/2000/svg" 
          fill="none" 
          viewBox="0 0 24 24"
        >
          <circle 
            className="opacity-25" 
            cx="12" 
            cy="12" 
            r="10" 
            stroke="currentColor" 
            strokeWidth="4"
          />
          <path 
            className="opacity-75" 
            fill="currentColor" 
            d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"
          />
        </svg>
      )}
      {loading ? loadingText : text}
    </button>
  );
};

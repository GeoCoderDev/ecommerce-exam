/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {
      colors: {
        'blue-light': '#63A4FA',       // Azul claro
        'brown-dark': '#9A5021',       // Marrón cálido
        'background': '#F4F4F9',        // Gris claro
        'yellow-soft': '#FFC857',      // Amarillo suave
        'text-dark': '#2B2D42',        // Gris oscuro
        'text-black': '#333333',       // Negro oscuro
        'green-soft': '#A4D18E',       // Verde suave
        'brown-darker': '#6E3515',     // Marrón oscuro
      },
    },
  },
  plugins: [],
}

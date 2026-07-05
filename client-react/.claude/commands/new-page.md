Create a new page component for this e-commerce React project.

The page name is: $ARGUMENTS

Follow these conventions:
1. Create the file at `src/pages/<PageName>/index.tsx`
2. Use a functional component (no React.FC type annotation, consistent with existing pages)
3. The component name should match the directory name in PascalCase
4. Add the route to `src/routes.tsx` inside the existing `<Route element={<Layout />}>` block
5. Use an appropriate path (e.g., PageName → /page-name)

After creating the files, show the user:
- The created file path
- The route that was added
- A reminder to import the component in routes.tsx if not already done automatically

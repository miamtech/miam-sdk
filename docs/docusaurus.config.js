// @ts-check
// Note: type annotations allow type checking and IDEs autocompletion

/** @type {import('@docusaurus/types').Config} */
const config = {
  title: 'Miam mobile SDK',
  tagline: 'miam for native ios and android apps',
  favicon: 'img/favicon.ico',
  url: 'https://miamtech.github.io/',
  baseUrl: '/kmm-miam-sdk/',
  organizationName: 'miamtech', 
  projectName: 'kmm-miam-sdk', 
  deploymentBranch: 'main',
  trailingSlash: false,
  

  onBrokenLinks: 'warn',
  onBrokenMarkdownLinks: 'warn',

  i18n: {
    defaultLocale: 'en',
    locales: ['en'],
  },

  presets: [
    [
      'classic',
      /** @type {import('@docusaurus/preset-classic').Options} */
      ({
        docs: {
         
          sidebarPath: require.resolve('./sidebars.js'),
         
        },
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
      }),
    ],
  ],

  themeConfig:
    /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
    ({
      // Replace with your project's social card
      image: 'img/docusaurus-social-card.jpg',
      prism: {
        additionalLanguages: ['kotlin', 'java','gradle','swift'],   
        },
      navbar: {
        style: 'dark',
        logo: {
          alt: 'My Site Logo',
          src: 'img/logo.svg',
        },
        items: [
          {
            type: 'doc',
            docId: 'android/Introduction',
            position: 'left',
            label: 'For Android',
          },
          {
            type: 'doc',
            docId: 'ios/Introduction',
            position: 'left',
            label: 'For Ios',
          },
          {to: 'https://en.miam.tech/', label: 'Enterprise', position: 'left'},
         
        ],
      },
      footer: {
        style: 'dark',
        links: [
          {
            title: 'Docs',
            items: [
              {
                label: 'android',
                to: 'android/Introduction',
              },
              {
                label: 'ios',
                to: 'ios/Introduction',
              },
            ],
          },
          {
            title: 'More',
            items: [
              {
                label: 'GitHub',
                href: 'https://github.com/miamtech/kmm-miam-sdk',
              },
            ],
          },
        ],
        copyright: `Copyright © ${new Date().getFullYear()} Miam, Inc. Built with Docusaurus.`,
      },
    
    }),
    
};

module.exports = config;

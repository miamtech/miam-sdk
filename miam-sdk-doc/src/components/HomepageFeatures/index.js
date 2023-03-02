import React from 'react';
import clsx from 'clsx';
import styles from './styles.module.css';

const FeatureList = [
  {
    title: 'Easy to Use',
    Svg: require('@site/static/img/plugAndPlay.svg').default,
    description: (
      <>
        Miam provide native view that can be plugged in any configuration XML, Jetpack Compose, UIkit or Swiftui.
      </>
    ),
  },
  {
    title: 'Focus on What Matters',
    Svg: require('@site/static/img/iaBuildin.svg').default,
    description: (
      <>
        Miam SDK embed pre build component design to your application needs
      </>
    ),
  },
  {
    title: 'Fit your Style',
    Svg: require('@site/static/img/UI.svg').default,
    description: (
      <>
        Miam experience and design is fully customisable 
      </>
    ),
  },

];

function Feature({Svg, title, description}) {
  return (
    <div className={clsx('col col--4')}>
      <div className="text--center">
        <Svg className={styles.featureSvg} role="img" />
      </div>
      <div className="text--center padding-horiz--md">
        <h3>{title}</h3>
        <p>{description}</p>
      </div>
    </div>
  );
}

export default function HomepageFeatures() {
  return (
    <section className={styles.features}>
      <div className="container">
        <div className="row">
          {FeatureList.map((props, idx) => (
            <Feature key={idx} {...props} />
          ))}
        </div>
      </div>
    </section>
  );
}

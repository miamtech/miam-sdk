#import <Foundation/NSArray.h>
#import <Foundation/NSDictionary.h>
#import <Foundation/NSError.h>
#import <Foundation/NSObject.h>
#import <Foundation/NSSet.h>
#import <Foundation/NSString.h>
#import <Foundation/NSValue.h>

@class MiamCoreKoin_coreKoin, MiamCoreAnalyticsCompanion, MiamCoreAnalyticsPlausibleProps, MiamCoreAnalyticsPlausibleEventCompanion, MiamCoreAnalyticsPlausibleEvent, MiamCoreAnalyticsPlausiblePropsCompanion, MiamCoreRoute, MiamCoreDialogRoute, MiamCorePageRoute, MiamCoreRouteServiceAction, MiamCoreRouteServiceActionSetDialogRoute, MiamCoreRouteServiceActionSetPageRoute, MiamCoreRouteServiceEffect, MiamCoreRouteServiceEffectRouteChanged, MiamCoreRouteServiceInstance, MiamCoreRouteService, MiamCoreRouteServiceState, MiamCoreUserPreferencesInstance, MiamCoreUserPreferences, MiamCoreKotlinx_coroutines_coreCoroutineDispatcher, MiamCoreKotlinAbstractCoroutineContextElement, MiamCoreKotlinx_coroutines_coreCoroutineDispatcherKey, MiamCoreMainLoopDispatcher, MiamCoreAttributesCompanion, MiamCoreKotlinArray<T>, MiamCoreRecordCompanion, MiamCoreKotlinx_serialization_jsonJsonElement, MiamCoreAttributes, MiamCoreRelationships, MiamCoreRecord, MiamCoreBasketCompanion, MiamCoreBasketAttributes, MiamCoreBasketRelationships, MiamCoreRecipe, MiamCoreBasket, MiamCoreBasketEntry, MiamCoreBasketCompletion, MiamCoreBasketAttributesCompanion, MiamCoreBasketCompletionCompanion, MiamCoreBasketEntriesItemCompanion, MiamCoreBasketEntriesItem, MiamCoreBasketEntryCompanion, MiamCoreBasketEntryAttributes, MiamCoreBasketEntryRelationships, MiamCoreItem, MiamCoreBasketEntryAttributesCompanion, MiamCoreRelationshipListCompanion, MiamCoreRelationshipList, MiamCoreBasketEntryRelationshipListCompanion, MiamCoreBasketEntryRelationshipListSerializer, MiamCoreBasketEntryRelationshipList, MiamCoreRelationshipsCompanion, MiamCoreItemRelationshipList, MiamCoreGroceriesEntryRelationship, MiamCoreBasketEntryRelationshipsCompanion, MiamCoreLineEntries, MiamCoreBasketPreviewLineCompanion, MiamCoreBasketPreviewLine, MiamCoreGroceriesList, MiamCoreBasketRelationshipsCompanion, MiamCoreCatalogFilterOptions, MiamCoreTagTypes, MiamCoreTag, MiamCoreCheckableTagCompanion, MiamCoreCheckableTag, MiamCoreGroceriesEntryCompanion, MiamCoreGroceriesEntryAttributes, MiamCoreGroceriesEntryRelationships, MiamCoreGroceriesEntry, MiamCoreGroceriesEntryAttributesCompanion, MiamCoreRelationshipCompanion, MiamCoreRelationship, MiamCoreGroceriesEntryRelationshipCompanion, MiamCoreGroceriesEntryRelationshipListCompanion, MiamCoreGroceriesEntryRelationshipListSerializer, MiamCoreGroceriesEntryRelationshipList, MiamCoreGroceriesEntryRelationshipSerializer, MiamCoreGroceriesEntryRelationshipsCompanion, MiamCoreGroceriesListCompanion, MiamCoreGroceriesListAttributes, MiamCoreGroceriesListRelationships, MiamCoreRecipeInfos, MiamCoreGroceriesListAttributesCompanion, MiamCoreGroceriesListRelationshipsCompanion, MiamCoreIngredientCompanion, MiamCoreIngredientAttributes, MiamCoreIngredientRelationships, MiamCoreIngredient, MiamCoreIngredientAttributesCompanion, MiamCoreIngredientListRelationshipCompanion, MiamCoreIngredientListSerializer, MiamCoreIngredientListRelationship, MiamCoreIngredientRelationshipsCompanion, MiamCoreItemCompanion, MiamCoreItemAttributes, MiamCoreItemRelationships, MiamCoreItemAttributesCompanion, MiamCoreItemRelationshipListCompanion, MiamCoreItemRelationshipListSerializer, MiamCoreItemRelationshipsCompanion, MiamCorePackageCompanion, MiamCorePackage, MiamCorePackageAttributes, MiamCorePackageRelationships, MiamCorePackageSettings, MiamCorePackageAttributesCompanion, MiamCoreRecipeRelationshipList, MiamCorePackageRelationshipsCompanion, MiamCorePackageSettingsCompanion, MiamCorePointOfSaleAttributes, MiamCorePointOfSaleCompanion, MiamCorePointOfSale, MiamCorePointOfSaleSettings, MiamCoreSupplier, MiamCorePointOfSaleAttributesCompanion, MiamCorePointOfSaleOptionCompanion, MiamCorePointOfSaleOption, MiamCorePointOfSaleSettingsCompanion, MiamCorePointOfSaleWrapperCompanion, MiamCorePointOfSaleWrapper, MiamCorePointOfSalesCompanion, MiamCorePointOfSales, MiamCorePricingCompanion, MiamCorePricing, MiamCoreRecipeCompanion, MiamCoreRecipeAttributes, MiamCoreRecipeRelationships, MiamCoreRecipeLike, MiamCoreRecipeStep, MiamCoreRecipeAttributesCompanion, MiamCoreRecipeInfosCompanion, MiamCoreRecipeLikeCompanion, MiamCoreRecipeLikeAttributes, MiamCoreRecipeLikeRelationships, MiamCoreRecipeLikeAttributesCompanion, MiamCoreRecipeLikeRelationshipsCompanion, MiamCoreRecipeProviderCompanion, MiamCoreRecipeProviderAttributes, MiamCoreRecipeProviderRelationships, MiamCoreRecipeProvider, MiamCoreRecipeProviderAttributesCompanion, MiamCoreRecipeProviderRelationshipCompanion, MiamCoreRecipeProviderRelationshipsCompanion, MiamCoreRecipeProviderSerializer, MiamCoreRecipeProviderRelationship, MiamCoreRecipeRelationshipListCompanion, MiamCoreRecipeRelationshipListSerializer, MiamCoreRecipeStatusRelationship, MiamCoreSponsorListRelationship, MiamCoreRecipeStepListRelationship, MiamCoreRecipeTypeRelationship, MiamCoreRecipeRelationshipsCompanion, MiamCoreRecipeStatusCompanion, MiamCoreRecipeStatusAttributes, MiamCoreRecipeStatusRelationships, MiamCoreRecipeStatus, MiamCoreRecipeStatusAttributesCompanion, MiamCoreRecipeStatusRelationshipCompanion, MiamCoreRecipeStatusRelationshipsCompanion, MiamCoreRecipeStatusSerializer, MiamCoreRecipeStepAttributes, MiamCoreRecipeStepRelationships, MiamCoreRecipeStepCompanion, MiamCoreRecipeStepAttributesCompanion, MiamCoreRecipeStepListListSerializer, MiamCoreRecipeStepListRelationshipCompanion, MiamCoreRecipeStepRelationshipsCompanion, MiamCoreRecipeTypeCompanion, MiamCoreRecipeTypeAttributes, MiamCoreRecipeTypeRelationships, MiamCoreRecipeType, MiamCoreRecipeTypeAttributesCompanion, MiamCoreRecipeTypeRelationshipCompanion, MiamCoreRecipeTypeRelationshipsCompanion, MiamCoreRecipeTypeSerializer, MiamCoreRecordLink, MiamCoreRecordCounterWrapperCompanion, MiamCoreRecordCounterWrapper, MiamCoreRecordLinkCompanion, MiamCoreRecordWrapperCompanion, MiamCoreRecordWrapper, MiamCoreRetailerProduct, MiamCoreSponsorCompanion, MiamCoreSponsorAttributes, MiamCoreSponsorRelationships, MiamCoreSponsor, MiamCoreSponsorAttributesCompanion, MiamCoreSponsorListRelationshipCompanion, MiamCoreSponsorListSerializer, MiamCoreSponsorRelationshipsCompanion, MiamCoreSuggestionsCriteriaCompanion, MiamCoreSuggestionsCriteria, MiamCoreSupplierAttributes, MiamCoreSupplierCompanion, MiamCoreSupplierAttributesCompanion, MiamCoreSupplierNotificationWrapperCompanion, MiamCoreSupplierNotificationWrapper, MiamCoreSupplierWrapperCompanion, MiamCoreSupplierWrapper, MiamCoreTagAttributes, MiamCoreTagRelationships, MiamCoreTagCompanion, MiamCoreTagAttributesCompanion, MiamCoreTagRelationshipsCompanion, MiamCoreKotlinEnumCompanion, MiamCoreKotlinEnum<E>, MiamCoreDurationSerializer, MiamCoreMiamAPIDatasource, MiamCoreRecipeRepositoryImpCompanion, MiamCoreKotlinUnit, MiamCoreTagsRepositoryImpCompanion, MiamCoreHttpRoutes, MiamCoreKotlinThrowable, MiamCoreMainIoExecutor, MiamCoreBaseViewModel<Event, State, Effect>, MiamCoreRecipeLikeContractState, MiamCoreRecipeLikeContractEvent, MiamCoreRecipeLikeContractEffect, MiamCoreBasicUiState<__covariant T>, MiamCoreQuantityFormatterDefault, MiamCoreBasketPreviewContractEvent, MiamCoreBasketPreviewContractEventAddEntry, MiamCoreBasketPreviewContractEventCloseItemSelector, MiamCoreBasketPreviewContractEventKillJob, MiamCoreBasketPreviewContractEventOpenItemSelector, MiamCoreBasketPreviewContractEventRemoveRecipe, MiamCoreBasketPreviewContractEventReplaceItem, MiamCoreBasketPreviewContractEventSetLines, MiamCoreBasketPreviewContractEventSetRecipeId, MiamCoreBasketPreviewContractEventToggleLine, MiamCoreBasketPreviewContractState, MiamCoreBasketPreviewContractEffect, MiamCoreBasketPreviewViewModelLineUpdateState, MiamCoreItemSelectorContractEvent, MiamCoreItemSelectorContractEventReturnToBasketPreview, MiamCoreItemSelectorContractEventSelectNewItem, MiamCoreItemSelectorContractEventSetItemList, MiamCoreItemSelectorContractEventSetReplaceItemInPreview, MiamCoreItemSelectorContractEventSetReturnToBasketPreview, MiamCoreItemSelectorContractEventSetSelectedItem, MiamCoreItemSelectorContractState, MiamCoreItemSelectorInstance, MiamCoreItemSelectorViewModel, MiamCoreItemSelectorContractEffect, MiamCoreFavoritePageContractEvent, MiamCoreFavoritePageContractEventLoadPage, MiamCoreFavoritePageContractState, MiamCoreFavoritePageViewModelCompanion, MiamCoreFavoritePageContractEffect, MiamCoreMyMealContractEvent, MiamCoreMyMealContractEventRemoveRecipe, MiamCoreMyMealContractState, MiamCoreMyMealContractEffect, MiamCorePricingContractState, MiamCorePricingContractEvent, MiamCorePricingContractEffect, MiamCoreRecipeListPageContractEvent, MiamCoreRecipeListPageContractEventInitPage, MiamCoreRecipeListPageContractEventLoadPage, MiamCoreRecipeListPageContractState, MiamCoreRecipeListPageContractEffect, MiamCoreMyMealButtonContractState, MiamCoreMyMealButtonContractEvent, MiamCoreMyMealButtonContractEffect, MiamCorePreferencesContractState, MiamCorePreferencesEffect, MiamCorePreferencesEffectPreferencesChanged, MiamCorePreferencesEffectPreferencesLoaded, MiamCorePreferencesViewModelInstance, MiamCoreSingletonPreferencesViewModel, MiamCoreSingletonPreferencesViewModelCompanion, MiamCorePreferencesContractEvent, MiamCorePreferencesContractEffect, MiamCoreRouterContent, MiamCoreRouterOutletContractEvent, MiamCoreRecipeViewModel, MiamCoreRouterOutletContractEventGoToDetail, MiamCoreRouterOutletContractEventGoToHelper, MiamCoreRouterOutletContractEventGoToItemSelector, MiamCoreRouterOutletContractEventGoToPreview, MiamCoreRouterOutletContractEventGoToSponsor, MiamCoreRouterOutletContractEventOpenDialog, MiamCoreRouterOutletContractEventPrevious, MiamCoreRouterOutletContractState, MiamCoreRouterOutletContractEffect, MiamCorePreferencesSearchContractState, MiamCorePreferencesSearchContractEvent, MiamCorePreferencesSearchContractEffect, MiamCoreRecipeCarouselContractEvent, MiamCoreRecipeCarouselContractEventGetRecipeSuggestionsFromCriteria, MiamCoreRecipeCarouselContractEventGetRecipeSuggestionsFromId, MiamCoreRecipeCarouselContractState, MiamCoreRecipeCarouselContractEffect, MiamCoreFilterViewModelInstance, MiamCoreSingletonFilterViewModel, MiamCoreSingletonFilterContractEffect, MiamCoreSingletonFilterContractEffectOnUpdate, MiamCoreSingletonFilterContractEvent, MiamCoreSingletonFilterContractEventOnCostFilterChanged, MiamCoreSingletonFilterContractEventOnDifficultyChanged, MiamCoreSingletonFilterContractEventOnTimeFilterChanged, MiamCoreSingletonFilterContractState, MiamCoreSingletonFilterViewModelCompanion, MiamCoreBasketTagContractEvent, MiamCoreBasketTagContractEventSetRetailerProductId, MiamCoreBasketTagContractState, MiamCoreRouterOutletViewModel, MiamCoreBasketTagContractEffect, MiamCoreRecipeContractEffect, MiamCoreRecipeContractEffectDisliked, MiamCoreRecipeContractEffectHideCard, MiamCoreRecipeContractEvent, MiamCoreRecipeContractEventError, MiamCoreRecipeContractEventOnAddRecipe, MiamCoreRecipeContractEventSetActiveStep, MiamCoreRecipeContractEventShowIngredient, MiamCoreRecipeContractEventShowSteps, MiamCoreTabEnum, MiamCoreRecipeContractState, MiamCoreGroceriesListStore, MiamCoreCatalogContent, MiamCoreCatalogContractEvent, MiamCoreCatalogContractEventGoBack, MiamCoreCatalogContractEventGoToFavorite, MiamCoreDialogContent, MiamCoreCatalogContractState, MiamCoreCatalogViewModelCompanion, MiamCoreCatalogContractEffect, MiamCoreAlterQuantityBasketEntry, MiamCoreBasicUiStateEmpty, MiamCoreKotlinNothing, MiamCoreBasicUiStateError, MiamCoreBasicUiStateIdle, MiamCoreBasicUiStateLoading, MiamCoreBasicUiStateSuccess<T>, MiamCoreBasketAction, MiamCoreBasketActionAddBasketEntry, MiamCoreBasketActionConfirmBasket, MiamCoreBasketActionRefreshBasket, MiamCoreBasketActionRemoveEntry, MiamCoreBasketActionReplaceSelectedItem, MiamCoreBasketActionUpdateBasketEntries, MiamCoreBasketActionUpdateBasketEntriesDiff, MiamCoreBasketEffect, MiamCoreBasketEffectBasketConfirmed, MiamCoreBasketEffectBasketPreviewChange, MiamCoreBasketState, MiamCoreGroceriesListAction, MiamCoreGroceriesListActionAlterRecipeList, MiamCoreGroceriesListActionRefreshGroceriesList, MiamCoreGroceriesListActionRemoveRecipe, MiamCoreGroceriesListActionResetGroceriesList, MiamCoreGroceriesListEffect, MiamCoreGroceriesListEffectGroceriesListLoaded, MiamCoreGroceriesListEffectRecipeAdded, MiamCoreGroceriesListEffectRecipeCountChanged, MiamCoreGroceriesListEffectRecipeRemoved, MiamCoreGroceriesListState, MiamCoreLikeEffect, MiamCoreLikeEffectDisliked, MiamCoreLikeEffectLiked, MiamCoreLikeStoreInstance, MiamCoreLikeStore, MiamCorePointOfSaleAction, MiamCorePointOfSaleActionSetExtId, MiamCorePointOfSaleActionSetSupplierId, MiamCorePointOfSaleState, MiamCoreUserAction, MiamCoreUserActionRefreshUser, MiamCoreUserState, MiamCoreExecutorHelperCompanion, NSObject, MiamCoreReadyEvent, MiamCoreContextHandlerInstance, MiamCoreContextHandler, MiamCoreContextHandlerState, MiamCoreGroceriesListHandler, MiamCoreLogHandlerCompanion, MiamCorePointOfSaleHandler, MiamCoreCatalogCategory, MiamCoreReadyEventIsNotReady, MiamCoreReadyEventIsReady, MiamCoreToasterHandler, MiamCoreToasterState, MiamCoreUserHandler, MiamCoreBasketComparisonItem, MiamCoreBasketComparator, MiamCoreBasketComparatorData, MiamCoreBasketHandlerInstance, MiamCoreBasketHandler, MiamCoreBasketHandlerState, MiamCoreKoin_coreKoinApplication, MiamCoreKoin_coreModule, MiamCoreKotlinx_serialization_jsonJson, MiamCoreKoin_coreScope, MiamCoreKoin_coreParametersHolder, MiamCoreKotlinLazyThreadSafetyMode, MiamCoreKoin_coreLogger, MiamCoreKoin_coreInstanceRegistry, MiamCoreKoin_corePropertyRegistry, MiamCoreKoin_coreScopeRegistry, MiamCoreKotlinCancellationException, MiamCoreKotlinAbstractCoroutineContextKey<B, E>, MiamCoreKotlinx_serialization_jsonJsonElementCompanion, MiamCoreKotlinx_serialization_coreSerializersModule, MiamCoreKotlinx_serialization_coreSerialKind, MiamCoreKotlinException, MiamCoreKotlinRuntimeException, MiamCoreKotlinIllegalStateException, MiamCoreKoin_coreKoinApplicationCompanion, MiamCoreKoin_coreLevel, MiamCoreKoin_coreInstanceFactory<T>, MiamCoreKotlinPair<__covariant A, __covariant B>, MiamCoreKoin_coreScopeDSL, MiamCoreKoin_coreSingleInstanceFactory<T>, MiamCoreKotlinx_serialization_jsonJsonConfiguration, MiamCoreKotlinx_serialization_jsonJsonDefault, MiamCoreKoin_coreLockable, MiamCoreKoin_coreParametersHolderCompanion, MiamCoreKoin_coreScopeRegistryCompanion, MiamCoreKoin_coreBeanDefinition<T>, MiamCoreKoin_coreInstanceFactoryCompanion, MiamCoreKoin_coreInstanceContext, MiamCoreKotlinx_coroutines_coreAtomicDesc, MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNodePrepareOp, MiamCoreKoin_coreKind, MiamCoreKoin_coreCallbacks<T>, MiamCoreKotlinx_coroutines_coreAtomicOp<__contravariant T>, MiamCoreKotlinx_coroutines_coreOpDescriptor, MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode, MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNodeAbstractAtomicDesc, MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNodeAddLastDesc<T>, MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNodeRemoveFirstDesc<T>;

@protocol MiamCoreKoin_coreKoinComponent, MiamCoreKotlinx_coroutines_coreMutableStateFlow, MiamCoreKotlinx_serialization_coreKSerializer, MiamCoreKotlinx_coroutines_coreJob, MiamCoreAction, MiamCoreKotlinx_coroutines_coreFlow, MiamCoreKotlinx_coroutines_coreStateFlow, MiamCoreState, MiamCoreStore, MiamCoreKotlinCoroutineContext, MiamCoreKotlinx_coroutines_coreCoroutineScope, MiamCoreEffect, MiamCoreKotlinCoroutineContextKey, MiamCoreKotlinCoroutineContextElement, MiamCoreKotlinContinuation, MiamCoreKotlinContinuationInterceptor, MiamCoreKotlinx_coroutines_coreRunnable, MiamCoreBasketPreviewEntry, MiamCoreKotlinKClass, MiamCoreKotlinx_serialization_coreEncoder, MiamCoreKotlinx_serialization_coreSerialDescriptor, MiamCoreKotlinx_serialization_coreSerializationStrategy, MiamCoreKotlinx_serialization_coreDecoder, MiamCoreKotlinx_serialization_coreDeserializationStrategy, MiamCoreKotlinComparable, MiamCoreBasketEntryRepository, MiamCoreBasketRepository, MiamCoreGroceriesEntryRepository, MiamCoreGroceriesListRepository, MiamCorePackageRepository, MiamCorePointOfSaleRepository, MiamCorePricingRepository, MiamCoreRecipeRepository, MiamCoreRecipeDataSource, MiamCoreGroceriesListDataSource, MiamCorePointOfSaleDataSource, MiamCoreBasketDataSource, MiamCorePricingDataSource, MiamCoreBasketEntryDataSource, MiamCoreGrocerieEntryDataSource, MiamCoreSupplierDataSource, MiamCorePackageDataSource, MiamCoreTagDataSource, MiamCoreIExecutorScope, MiamCoreKotlinx_coroutines_coreSharedFlow, MiamCoreUiEffect, MiamCoreUiEvent, MiamCoreUiState, MiamCoreKoin_coreKoinScopeComponent, MiamCoreKoin_coreQualifier, MiamCoreKotlinLazy, MiamCoreKotlinx_coroutines_coreFlowCollector, MiamCoreKotlinx_coroutines_coreMutableSharedFlow, MiamCoreKotlinx_coroutines_coreChildHandle, MiamCoreKotlinx_coroutines_coreChildJob, MiamCoreKotlinx_coroutines_coreDisposableHandle, MiamCoreKotlinSequence, MiamCoreKotlinx_coroutines_coreSelectClause0, MiamCoreKotlinIterator, MiamCoreKotlinKDeclarationContainer, MiamCoreKotlinKAnnotatedElement, MiamCoreKotlinKClassifier, MiamCoreKotlinx_serialization_coreCompositeEncoder, MiamCoreKotlinAnnotation, MiamCoreKotlinx_serialization_coreCompositeDecoder, MiamCoreKotlinx_serialization_coreSerialFormat, MiamCoreKotlinx_serialization_coreStringFormat, MiamCoreKoin_coreScopeCallback, MiamCoreKotlinx_coroutines_coreParentJob, MiamCoreKotlinx_coroutines_coreSelectInstance, MiamCoreKotlinSuspendFunction0, MiamCoreKotlinx_serialization_coreSerializersModuleCollector, MiamCoreKotlinFunction;

NS_ASSUME_NONNULL_BEGIN
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunknown-warning-option"
#pragma clang diagnostic ignored "-Wincompatible-property-type"
#pragma clang diagnostic ignored "-Wnullability"

#pragma push_macro("_Nullable_result")
#if !__has_feature(nullability_nullable_result)
#undef _Nullable_result
#define _Nullable_result _Nullable
#endif

__attribute__((swift_name("KotlinBase")))
@interface MiamCoreBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end;

@interface MiamCoreBase (MiamCoreBaseCopying) <NSCopying>
@end;

__attribute__((swift_name("KotlinMutableSet")))
@interface MiamCoreMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end;

__attribute__((swift_name("KotlinMutableDictionary")))
@interface MiamCoreMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end;

@interface NSError (NSErrorMiamCoreKotlinException)
@property (readonly) id _Nullable kotlinException;
@end;

__attribute__((swift_name("KotlinNumber")))
@interface MiamCoreNumber : NSNumber
- (instancetype)initWithChar:(char)value __attribute__((unavailable));
- (instancetype)initWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
- (instancetype)initWithShort:(short)value __attribute__((unavailable));
- (instancetype)initWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
- (instancetype)initWithInt:(int)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
- (instancetype)initWithLong:(long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
- (instancetype)initWithLongLong:(long long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
- (instancetype)initWithFloat:(float)value __attribute__((unavailable));
- (instancetype)initWithDouble:(double)value __attribute__((unavailable));
- (instancetype)initWithBool:(BOOL)value __attribute__((unavailable));
- (instancetype)initWithInteger:(NSInteger)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
+ (instancetype)numberWithChar:(char)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
+ (instancetype)numberWithShort:(short)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
+ (instancetype)numberWithInt:(int)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
+ (instancetype)numberWithLong:(long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
+ (instancetype)numberWithLongLong:(long long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
+ (instancetype)numberWithFloat:(float)value __attribute__((unavailable));
+ (instancetype)numberWithDouble:(double)value __attribute__((unavailable));
+ (instancetype)numberWithBool:(BOOL)value __attribute__((unavailable));
+ (instancetype)numberWithInteger:(NSInteger)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
@end;

__attribute__((swift_name("KotlinByte")))
@interface MiamCoreByte : MiamCoreNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end;

__attribute__((swift_name("KotlinUByte")))
@interface MiamCoreUByte : MiamCoreNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end;

__attribute__((swift_name("KotlinShort")))
@interface MiamCoreShort : MiamCoreNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end;

__attribute__((swift_name("KotlinUShort")))
@interface MiamCoreUShort : MiamCoreNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end;

__attribute__((swift_name("KotlinInt")))
@interface MiamCoreInt : MiamCoreNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end;

__attribute__((swift_name("KotlinUInt")))
@interface MiamCoreUInt : MiamCoreNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end;

__attribute__((swift_name("KotlinLong")))
@interface MiamCoreLong : MiamCoreNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end;

__attribute__((swift_name("KotlinULong")))
@interface MiamCoreULong : MiamCoreNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end;

__attribute__((swift_name("KotlinFloat")))
@interface MiamCoreFloat : MiamCoreNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end;

__attribute__((swift_name("KotlinDouble")))
@interface MiamCoreDouble : MiamCoreNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end;

__attribute__((swift_name("KotlinBoolean")))
@interface MiamCoreBoolean : MiamCoreNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end;

__attribute__((swift_name("Koin_coreKoinComponent")))
@protocol MiamCoreKoin_coreKoinComponent
@required
- (MiamCoreKoin_coreKoin *)getKoin __attribute__((swift_name("getKoin()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Analytics")))
@interface MiamCoreAnalytics : MiamCoreBase <MiamCoreKoin_coreKoinComponent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreAnalyticsCompanion *companion __attribute__((swift_name("companion")));
- (void)doInitSupplierOrigin:(NSString *)supplierOrigin __attribute__((swift_name("doInit(supplierOrigin:)")));
- (void)sendEventEventType:(NSString *)eventType path:(NSString *)path props:(MiamCoreAnalyticsPlausibleProps *)props __attribute__((swift_name("sendEvent(eventType:path:props:)")));
@property (readonly) id<MiamCoreKotlinx_coroutines_coreMutableStateFlow> domain __attribute__((swift_name("domain")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Analytics.Companion")))
@interface MiamCoreAnalyticsCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreAnalyticsCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) NSString *EVENT_BASKET_CONFIRMED __attribute__((swift_name("EVENT_BASKET_CONFIRMED")));
@property (readonly) NSString *EVENT_CATEGORY_SHOW __attribute__((swift_name("EVENT_CATEGORY_SHOW")));
@property (readonly) NSString *EVENT_ENTRY_ADD __attribute__((swift_name("EVENT_ENTRY_ADD")));
@property (readonly) NSString *EVENT_ENTRY_CHANGE_QUANTITY __attribute__((swift_name("EVENT_ENTRY_CHANGE_QUANTITY")));
@property (readonly) NSString *EVENT_ENTRY_DELETE __attribute__((swift_name("EVENT_ENTRY_DELETE")));
@property (readonly) NSString *EVENT_ENTRY_REPLACE __attribute__((swift_name("EVENT_ENTRY_REPLACE")));
@property (readonly) NSString *EVENT_PAGEVIEW __attribute__((swift_name("EVENT_PAGEVIEW")));
@property (readonly) NSString *EVENT_PAYMENT_CONFIRMED __attribute__((swift_name("EVENT_PAYMENT_CONFIRMED")));
@property (readonly) NSString *EVENT_PAYMENT_STARTED __attribute__((swift_name("EVENT_PAYMENT_STARTED")));
@property (readonly) NSString *EVENT_PERSONAL_RECIPE_CREATE __attribute__((swift_name("EVENT_PERSONAL_RECIPE_CREATE")));
@property (readonly) NSString *EVENT_PERSONAL_RECIPE_DELETE __attribute__((swift_name("EVENT_PERSONAL_RECIPE_DELETE")));
@property (readonly) NSString *EVENT_RECIPE_ADD __attribute__((swift_name("EVENT_RECIPE_ADD")));
@property (readonly) NSString *EVENT_RECIPE_CHANGEGUESTS __attribute__((swift_name("EVENT_RECIPE_CHANGEGUESTS")));
@property (readonly) NSString *EVENT_RECIPE_DISPLAY __attribute__((swift_name("EVENT_RECIPE_DISPLAY")));
@property (readonly) NSString *EVENT_RECIPE_LIKE __attribute__((swift_name("EVENT_RECIPE_LIKE")));
@property (readonly) NSString *EVENT_RECIPE_PRINT __attribute__((swift_name("EVENT_RECIPE_PRINT")));
@property (readonly) NSString *EVENT_RECIPE_REMOVE __attribute__((swift_name("EVENT_RECIPE_REMOVE")));
@property (readonly) NSString *EVENT_RECIPE_RESET __attribute__((swift_name("EVENT_RECIPE_RESET")));
@property (readonly) NSString *EVENT_RECIPE_SHOW __attribute__((swift_name("EVENT_RECIPE_SHOW")));
@property (readonly) NSString *EVENT_RECIPE_UNLIKE __attribute__((swift_name("EVENT_RECIPE_UNLIKE")));
@property (readonly) NSString *EVENT_SEARCH __attribute__((swift_name("EVENT_SEARCH")));
@property (readonly) NSString *PLAUSIBLE_URL __attribute__((swift_name("PLAUSIBLE_URL")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Analytics.PlausibleEvent")))
@interface MiamCoreAnalyticsPlausibleEvent : MiamCoreBase
- (instancetype)initWithName:(NSString *)name url:(NSString *)url domain:(NSString *)domain props:(MiamCoreAnalyticsPlausibleProps *)props __attribute__((swift_name("init(name:url:domain:props:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCoreAnalyticsPlausibleEventCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (MiamCoreAnalyticsPlausibleProps *)component4 __attribute__((swift_name("component4()")));
- (MiamCoreAnalyticsPlausibleEvent *)doCopyName:(NSString *)name url:(NSString *)url domain:(NSString *)domain props:(MiamCoreAnalyticsPlausibleProps *)props __attribute__((swift_name("doCopy(name:url:domain:props:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *domain __attribute__((swift_name("domain")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) MiamCoreAnalyticsPlausibleProps *props __attribute__((swift_name("props")));
@property (readonly) NSString *url __attribute__((swift_name("url")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Analytics.PlausibleEventCompanion")))
@interface MiamCoreAnalyticsPlausibleEventCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreAnalyticsPlausibleEventCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Analytics.PlausibleProps")))
@interface MiamCoreAnalyticsPlausibleProps : MiamCoreBase
- (instancetype)initWithRecipe_id:(NSString * _Nullable)recipe_id category_id:(NSString * _Nullable)category_id entry_name:(NSString * _Nullable)entry_name basket_id:(NSString * _Nullable)basket_id miam_amount:(MiamCoreFloat * _Nullable)miam_amount total_amount:(NSString * _Nullable)total_amount pos_id:(NSString * _Nullable)pos_id pos_total_amount:(NSString * _Nullable)pos_total_amount pos_name:(NSString * _Nullable)pos_name search_term:(NSString * _Nullable)search_term __attribute__((swift_name("init(recipe_id:category_id:entry_name:basket_id:miam_amount:total_amount:pos_id:pos_total_amount:pos_name:search_term:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCoreAnalyticsPlausiblePropsCompanion *companion __attribute__((swift_name("companion")));
- (NSString * _Nullable)component1 __attribute__((swift_name("component1()")));
- (NSString * _Nullable)component10 __attribute__((swift_name("component10()")));
- (NSString * _Nullable)component2 __attribute__((swift_name("component2()")));
- (NSString * _Nullable)component3 __attribute__((swift_name("component3()")));
- (NSString * _Nullable)component4 __attribute__((swift_name("component4()")));
- (MiamCoreFloat * _Nullable)component5 __attribute__((swift_name("component5()")));
- (NSString * _Nullable)component6 __attribute__((swift_name("component6()")));
- (NSString * _Nullable)component7 __attribute__((swift_name("component7()")));
- (NSString * _Nullable)component8 __attribute__((swift_name("component8()")));
- (NSString * _Nullable)component9 __attribute__((swift_name("component9()")));
- (MiamCoreAnalyticsPlausibleProps *)doCopyRecipe_id:(NSString * _Nullable)recipe_id category_id:(NSString * _Nullable)category_id entry_name:(NSString * _Nullable)entry_name basket_id:(NSString * _Nullable)basket_id miam_amount:(MiamCoreFloat * _Nullable)miam_amount total_amount:(NSString * _Nullable)total_amount pos_id:(NSString * _Nullable)pos_id pos_total_amount:(NSString * _Nullable)pos_total_amount pos_name:(NSString * _Nullable)pos_name search_term:(NSString * _Nullable)search_term __attribute__((swift_name("doCopy(recipe_id:category_id:entry_name:basket_id:miam_amount:total_amount:pos_id:pos_total_amount:pos_name:search_term:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable basket_id __attribute__((swift_name("basket_id")));
@property (readonly) NSString * _Nullable category_id __attribute__((swift_name("category_id")));
@property (readonly) NSString * _Nullable entry_name __attribute__((swift_name("entry_name")));
@property (readonly) MiamCoreFloat * _Nullable miam_amount __attribute__((swift_name("miam_amount")));
@property (readonly) NSString * _Nullable pos_id __attribute__((swift_name("pos_id")));
@property (readonly) NSString * _Nullable pos_name __attribute__((swift_name("pos_name")));
@property (readonly) NSString * _Nullable pos_total_amount __attribute__((swift_name("pos_total_amount")));
@property (readonly) NSString * _Nullable recipe_id __attribute__((swift_name("recipe_id")));
@property (readonly) NSString * _Nullable search_term __attribute__((swift_name("search_term")));
@property (readonly) NSString * _Nullable total_amount __attribute__((swift_name("total_amount")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Analytics.PlausiblePropsCompanion")))
@interface MiamCoreAnalyticsPlausiblePropsCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreAnalyticsPlausiblePropsCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((swift_name("Route")))
@interface MiamCoreRoute : MiamCoreBase
- (instancetype)initWithTitle:(NSString *)title backToRoute:(void (^)(void))backToRoute previous:(MiamCoreRoute * _Nullable)previous __attribute__((swift_name("init(title:backToRoute:previous:)"))) __attribute__((objc_designated_initializer));
- (void)onDialogBackRoute:(MiamCoreDialogRoute *)route __attribute__((swift_name("onDialogBack(route:)")));
- (MiamCorePageRoute * _Nullable)onDialogClose __attribute__((swift_name("onDialogClose()")));
- (void)onPrevious __attribute__((swift_name("onPrevious()")));
@property (readonly) void (^backToRoute)(void) __attribute__((swift_name("backToRoute")));
@property (readonly) MiamCoreRoute * _Nullable previous __attribute__((swift_name("previous")));
@property (readonly) NSString *title __attribute__((swift_name("title")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DialogRoute")))
@interface MiamCoreDialogRoute : MiamCoreRoute
- (instancetype)initWithTitle:(NSString *)title backToRoute:(void (^)(void))backToRoute previous:(MiamCoreRoute * _Nullable)previous closeDialog:(void (^)(void))closeDialog __attribute__((swift_name("init(title:backToRoute:previous:closeDialog:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithTitle:(NSString *)title backToRoute:(void (^)(void))backToRoute previous:(MiamCoreRoute * _Nullable)previous __attribute__((swift_name("init(title:backToRoute:previous:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (void (^)(void))component2 __attribute__((swift_name("component2()")));
- (MiamCoreRoute * _Nullable)component3 __attribute__((swift_name("component3()")));
- (void (^)(void))component4 __attribute__((swift_name("component4()")));
- (MiamCoreDialogRoute *)doCopyTitle:(NSString *)title backToRoute:(void (^)(void))backToRoute previous:(MiamCoreRoute * _Nullable)previous closeDialog:(void (^)(void))closeDialog __attribute__((swift_name("doCopy(title:backToRoute:previous:closeDialog:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (void)onDialogBackRoute:(MiamCoreDialogRoute *)route __attribute__((swift_name("onDialogBack(route:)")));
- (MiamCorePageRoute * _Nullable)onDialogClose __attribute__((swift_name("onDialogClose()")));
- (void)onPrevious __attribute__((swift_name("onPrevious()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) void (^backToRoute)(void) __attribute__((swift_name("backToRoute")));
@property (readonly) void (^closeDialog)(void) __attribute__((swift_name("closeDialog")));
@property (readonly) MiamCoreRoute * _Nullable previous __attribute__((swift_name("previous")));
@property (readonly) NSString *title __attribute__((swift_name("title")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PageRoute")))
@interface MiamCorePageRoute : MiamCoreRoute
- (instancetype)initWithTitle:(NSString *)title backToRoute:(void (^)(void))backToRoute previous:(MiamCoreRoute * _Nullable)previous __attribute__((swift_name("init(title:backToRoute:previous:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (void (^)(void))component2 __attribute__((swift_name("component2()")));
- (MiamCoreRoute * _Nullable)component3 __attribute__((swift_name("component3()")));
- (MiamCorePageRoute *)doCopyTitle:(NSString *)title backToRoute:(void (^)(void))backToRoute previous:(MiamCoreRoute * _Nullable)previous __attribute__((swift_name("doCopy(title:backToRoute:previous:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (void)onDialogBackRoute:(MiamCoreDialogRoute *)route __attribute__((swift_name("onDialogBack(route:)")));
- (MiamCorePageRoute *)onDialogClose __attribute__((swift_name("onDialogClose()")));
- (void)onPrevious __attribute__((swift_name("onPrevious()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) void (^backToRoute)(void) __attribute__((swift_name("backToRoute")));
@property (readonly) MiamCoreRoute * _Nullable previous __attribute__((swift_name("previous")));
@property (readonly) NSString *title __attribute__((swift_name("title")));
@end;

__attribute__((swift_name("Store")))
@protocol MiamCoreStore
@required
- (id<MiamCoreKotlinx_coroutines_coreJob>)dispatchAction:(id<MiamCoreAction>)action __attribute__((swift_name("dispatch(action:)")));
- (id<MiamCoreKotlinx_coroutines_coreFlow>)observeSideEffect __attribute__((swift_name("observeSideEffect()")));
- (id<MiamCoreKotlinx_coroutines_coreStateFlow>)observeState __attribute__((swift_name("observeState()")));
- (void)updateStateIfChangedNewState:(id<MiamCoreState>)newState __attribute__((swift_name("updateStateIfChanged(newState:)")));
@property (readonly) id<MiamCoreKotlinx_coroutines_coreMutableStateFlow> state __attribute__((swift_name("state")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreCoroutineScope")))
@protocol MiamCoreKotlinx_coroutines_coreCoroutineScope
@required
@property (readonly) id<MiamCoreKotlinCoroutineContext> coroutineContext __attribute__((swift_name("coroutineContext")));
@end;

__attribute__((swift_name("RouteService")))
@interface MiamCoreRouteService : MiamCoreBase <MiamCoreStore, MiamCoreKotlinx_coroutines_coreCoroutineScope>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (id<MiamCoreKotlinx_coroutines_coreJob>)dispatchAction:(MiamCoreRouteServiceAction *)action __attribute__((swift_name("dispatch(action:)")));
- (MiamCoreRoute * _Nullable)getCurrentRoute __attribute__((swift_name("getCurrentRoute()")));
- (id<MiamCoreKotlinx_coroutines_coreFlow>)observeSideEffect __attribute__((swift_name("observeSideEffect()")));
- (id<MiamCoreKotlinx_coroutines_coreStateFlow>)observeState __attribute__((swift_name("observeState()")));
- (void)onCloseDialog __attribute__((swift_name("onCloseDialog()")));
- (void)onRouteChangeUpdateRoute:(void (^)(MiamCoreRoute * _Nullable))updateRoute __attribute__((swift_name("onRouteChange(updateRoute:)")));
- (MiamCoreRoute * _Nullable)previous __attribute__((swift_name("previous()")));
@property (readonly) id<MiamCoreKotlinCoroutineContext> coroutineContext __attribute__((swift_name("coroutineContext")));
@property (readonly) id<MiamCoreKotlinx_coroutines_coreMutableStateFlow> state __attribute__((swift_name("state")));
@end;

__attribute__((swift_name("Action")))
@protocol MiamCoreAction
@required
@end;

__attribute__((swift_name("RouteServiceAction")))
@interface MiamCoreRouteServiceAction : MiamCoreBase <MiamCoreAction>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RouteServiceAction.SetDialogRoute")))
@interface MiamCoreRouteServiceActionSetDialogRoute : MiamCoreRouteServiceAction
- (instancetype)initWithTitle:(NSString *)title backToRoute:(void (^)(void))backToRoute closeDialog:(void (^)(void))closeDialog __attribute__((swift_name("init(title:backToRoute:closeDialog:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (void (^)(void))component2 __attribute__((swift_name("component2()")));
- (void (^)(void))component3 __attribute__((swift_name("component3()")));
- (MiamCoreRouteServiceActionSetDialogRoute *)doCopyTitle:(NSString *)title backToRoute:(void (^)(void))backToRoute closeDialog:(void (^)(void))closeDialog __attribute__((swift_name("doCopy(title:backToRoute:closeDialog:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) void (^backToRoute)(void) __attribute__((swift_name("backToRoute")));
@property (readonly) void (^closeDialog)(void) __attribute__((swift_name("closeDialog")));
@property (readonly) NSString *title __attribute__((swift_name("title")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RouteServiceAction.SetPageRoute")))
@interface MiamCoreRouteServiceActionSetPageRoute : MiamCoreRouteServiceAction
- (instancetype)initWithTitle:(NSString *)title backToRoute:(void (^)(void))backToRoute __attribute__((swift_name("init(title:backToRoute:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (void (^)(void))component2 __attribute__((swift_name("component2()")));
- (MiamCoreRouteServiceActionSetPageRoute *)doCopyTitle:(NSString *)title backToRoute:(void (^)(void))backToRoute __attribute__((swift_name("doCopy(title:backToRoute:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) void (^backToRoute)(void) __attribute__((swift_name("backToRoute")));
@property (readonly) NSString *title __attribute__((swift_name("title")));
@end;

__attribute__((swift_name("Effect")))
@protocol MiamCoreEffect
@required
@end;

__attribute__((swift_name("RouteServiceEffect")))
@interface MiamCoreRouteServiceEffect : MiamCoreBase <MiamCoreEffect>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RouteServiceEffect.RouteChanged")))
@interface MiamCoreRouteServiceEffectRouteChanged : MiamCoreRouteServiceEffect
- (instancetype)initWithNewRoute:(MiamCoreRoute *)newRoute __attribute__((swift_name("init(newRoute:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (MiamCoreRoute *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreRouteServiceEffectRouteChanged *)doCopyNewRoute:(MiamCoreRoute *)newRoute __attribute__((swift_name("doCopy(newRoute:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly, getter=doNewRoute) MiamCoreRoute *newRoute __attribute__((swift_name("newRoute")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RouteServiceInstance")))
@interface MiamCoreRouteServiceInstance : MiamCoreBase <MiamCoreKoin_coreKoinComponent>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)routeServiceInstance __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRouteServiceInstance *shared __attribute__((swift_name("shared")));
@property (readonly) MiamCoreRouteService *instance __attribute__((swift_name("instance")));
@end;

__attribute__((swift_name("State")))
@protocol MiamCoreState
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RouteServiceState")))
@interface MiamCoreRouteServiceState : MiamCoreBase <MiamCoreState>
- (instancetype)initWithRoute:(MiamCoreRoute * _Nullable)route __attribute__((swift_name("init(route:)"))) __attribute__((objc_designated_initializer));
- (MiamCoreRoute * _Nullable)component1 __attribute__((swift_name("component1()")));
- (MiamCoreRouteServiceState *)doCopyRoute:(MiamCoreRoute * _Nullable)route __attribute__((swift_name("doCopy(route:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreRoute * _Nullable route __attribute__((swift_name("route")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UserPreferences")))
@interface MiamCoreUserPreferences : MiamCoreBase <MiamCoreKoin_coreKoinComponent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (MiamCoreInt * _Nullable)getIntOrNullKey:(NSString *)key __attribute__((swift_name("getIntOrNull(key:)")));
- (NSArray<NSString *> * _Nullable)getListOrNullKey:(NSString *)key __attribute__((swift_name("getListOrNull(key:)")));
- (void)putIntKey:(NSString *)key value:(int32_t)value __attribute__((swift_name("putInt(key:value:)")));
- (void)putListKey:(NSString *)key value:(NSArray<NSString *> *)value __attribute__((swift_name("putList(key:value:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UserPreferencesInstance")))
@interface MiamCoreUserPreferencesInstance : MiamCoreBase <MiamCoreKoin_coreKoinComponent>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)userPreferencesInstance __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreUserPreferencesInstance *shared __attribute__((swift_name("shared")));
@property (readonly) MiamCoreUserPreferences *instance __attribute__((swift_name("instance")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MainDispatcher")))
@interface MiamCoreMainDispatcher : MiamCoreBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (readonly) MiamCoreKotlinx_coroutines_coreCoroutineDispatcher *dispatcher __attribute__((swift_name("dispatcher")));
@end;

__attribute__((swift_name("KotlinCoroutineContext")))
@protocol MiamCoreKotlinCoroutineContext
@required
- (id _Nullable)foldInitial:(id _Nullable)initial operation:(id _Nullable (^)(id _Nullable, id<MiamCoreKotlinCoroutineContextElement>))operation __attribute__((swift_name("fold(initial:operation:)")));
- (id<MiamCoreKotlinCoroutineContextElement> _Nullable)getKey:(id<MiamCoreKotlinCoroutineContextKey>)key __attribute__((swift_name("get(key:)")));
- (id<MiamCoreKotlinCoroutineContext>)minusKeyKey:(id<MiamCoreKotlinCoroutineContextKey>)key __attribute__((swift_name("minusKey(key:)")));
- (id<MiamCoreKotlinCoroutineContext>)plusContext:(id<MiamCoreKotlinCoroutineContext>)context __attribute__((swift_name("plus(context:)")));
@end;

__attribute__((swift_name("KotlinCoroutineContextElement")))
@protocol MiamCoreKotlinCoroutineContextElement <MiamCoreKotlinCoroutineContext>
@required
@property (readonly) id<MiamCoreKotlinCoroutineContextKey> key __attribute__((swift_name("key")));
@end;

__attribute__((swift_name("KotlinAbstractCoroutineContextElement")))
@interface MiamCoreKotlinAbstractCoroutineContextElement : MiamCoreBase <MiamCoreKotlinCoroutineContextElement>
- (instancetype)initWithKey:(id<MiamCoreKotlinCoroutineContextKey>)key __attribute__((swift_name("init(key:)"))) __attribute__((objc_designated_initializer));
@property (readonly) id<MiamCoreKotlinCoroutineContextKey> key __attribute__((swift_name("key")));
@end;

__attribute__((swift_name("KotlinContinuationInterceptor")))
@protocol MiamCoreKotlinContinuationInterceptor <MiamCoreKotlinCoroutineContextElement>
@required
- (id<MiamCoreKotlinContinuation>)interceptContinuationContinuation:(id<MiamCoreKotlinContinuation>)continuation __attribute__((swift_name("interceptContinuation(continuation:)")));
- (void)releaseInterceptedContinuationContinuation:(id<MiamCoreKotlinContinuation>)continuation __attribute__((swift_name("releaseInterceptedContinuation(continuation:)")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreCoroutineDispatcher")))
@interface MiamCoreKotlinx_coroutines_coreCoroutineDispatcher : MiamCoreKotlinAbstractCoroutineContextElement <MiamCoreKotlinContinuationInterceptor>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithKey:(id<MiamCoreKotlinCoroutineContextKey>)key __attribute__((swift_name("init(key:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreKotlinx_coroutines_coreCoroutineDispatcherKey *companion __attribute__((swift_name("companion")));
- (void)dispatchContext:(id<MiamCoreKotlinCoroutineContext>)context block:(id<MiamCoreKotlinx_coroutines_coreRunnable>)block __attribute__((swift_name("dispatch(context:block:)")));
- (void)dispatchYieldContext:(id<MiamCoreKotlinCoroutineContext>)context block:(id<MiamCoreKotlinx_coroutines_coreRunnable>)block __attribute__((swift_name("dispatchYield(context:block:)")));
- (id<MiamCoreKotlinContinuation>)interceptContinuationContinuation:(id<MiamCoreKotlinContinuation>)continuation __attribute__((swift_name("interceptContinuation(continuation:)")));
- (BOOL)isDispatchNeededContext:(id<MiamCoreKotlinCoroutineContext>)context __attribute__((swift_name("isDispatchNeeded(context:)")));
- (MiamCoreKotlinx_coroutines_coreCoroutineDispatcher *)plusOther:(MiamCoreKotlinx_coroutines_coreCoroutineDispatcher *)other __attribute__((swift_name("plus(other:)"))) __attribute__((unavailable("Operator '+' on two CoroutineDispatcher objects is meaningless. CoroutineDispatcher is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The dispatcher to the right of `+` just replaces the dispatcher to the left.")));
- (void)releaseInterceptedContinuationContinuation:(id<MiamCoreKotlinContinuation>)continuation __attribute__((swift_name("releaseInterceptedContinuation(continuation:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MainLoopDispatcher")))
@interface MiamCoreMainLoopDispatcher : MiamCoreKotlinx_coroutines_coreCoroutineDispatcher
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)mainLoopDispatcher __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreMainLoopDispatcher *shared __attribute__((swift_name("shared")));
- (void)dispatchContext:(id<MiamCoreKotlinCoroutineContext>)context block:(id<MiamCoreKotlinx_coroutines_coreRunnable>)block __attribute__((swift_name("dispatch(context:block:)")));
@end;

__attribute__((swift_name("Attributes")))
@interface MiamCoreAttributes : MiamCoreBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreAttributesCompanion *companion __attribute__((swift_name("companion")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Attributes.Companion")))
@interface MiamCoreAttributesCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreAttributesCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializerTypeParamsSerializers:(MiamCoreKotlinArray<id<MiamCoreKotlinx_serialization_coreKSerializer>> *)typeParamsSerializers __attribute__((swift_name("serializer(typeParamsSerializers:)")));
@end;

__attribute__((swift_name("Record")))
@interface MiamCoreRecord : MiamCoreBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreRecordCompanion *companion __attribute__((swift_name("companion")));
- (MiamCoreKotlinx_serialization_jsonJsonElement *)toJsonElement __attribute__((swift_name("toJsonElement()")));
@property (readonly) MiamCoreAttributes * _Nullable attributes __attribute__((swift_name("attributes")));
@property (readonly) NSString * _Nullable id __attribute__((swift_name("id")));
@property (readonly) MiamCoreRelationships * _Nullable relationships __attribute__((swift_name("relationships")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Basket")))
@interface MiamCoreBasket : MiamCoreRecord
- (instancetype)initWithId:(NSString *)id attributes:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)attributes json_relationships:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)json_relationships includedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("init(id:attributes:json_relationships:includedRecords:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreBasketCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreBasketAttributes * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreBasketRelationships * _Nullable)component3 __attribute__((swift_name("component3()")));
- (NSArray<MiamCoreRecipe *> *)component4 __attribute__((swift_name("component4()")));
- (MiamCoreBasket *)doCopyId:(NSString *)id attributes:(MiamCoreBasketAttributes * _Nullable)attributes relationships:(MiamCoreBasketRelationships * _Nullable)relationships recipes:(NSArray<MiamCoreRecipe *> *)recipes __attribute__((swift_name("doCopy(id:attributes:relationships:recipes:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
- (MiamCoreBasket *)updateBasketEntryNewBe:(MiamCoreBasketEntry *)newBe __attribute__((swift_name("updateBasketEntry(newBe:)")));
@property MiamCoreBasketAttributes * _Nullable attributes __attribute__((swift_name("attributes")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property NSArray<MiamCoreRecipe *> *recipes __attribute__((swift_name("recipes")));
@property (readonly) MiamCoreBasketRelationships * _Nullable relationships __attribute__((swift_name("relationships")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Basket.Companion")))
@interface MiamCoreBasketCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreBasketCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketAttributes")))
@interface MiamCoreBasketAttributes : MiamCoreAttributes
- (instancetype)initWithName:(NSString * _Nullable)name confirmed:(MiamCoreBoolean * _Nullable)confirmed completion:(MiamCoreBasketCompletion * _Nullable)completion totalPrice:(float)totalPrice capacityFactor:(MiamCoreInt * _Nullable)capacityFactor token:(NSString * _Nullable)token __attribute__((swift_name("init(name:confirmed:completion:totalPrice:capacityFactor:token:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreBasketAttributesCompanion *companion __attribute__((swift_name("companion")));
- (NSString * _Nullable)component1 __attribute__((swift_name("component1()")));
- (MiamCoreBoolean * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreBasketCompletion * _Nullable)component3 __attribute__((swift_name("component3()")));
- (float)component4 __attribute__((swift_name("component4()")));
- (MiamCoreInt * _Nullable)component5 __attribute__((swift_name("component5()")));
- (NSString * _Nullable)component6 __attribute__((swift_name("component6()")));
- (MiamCoreBasketAttributes *)doCopyName:(NSString * _Nullable)name confirmed:(MiamCoreBoolean * _Nullable)confirmed completion:(MiamCoreBasketCompletion * _Nullable)completion totalPrice:(float)totalPrice capacityFactor:(MiamCoreInt * _Nullable)capacityFactor token:(NSString * _Nullable)token __attribute__((swift_name("doCopy(name:confirmed:completion:totalPrice:capacityFactor:token:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreInt * _Nullable capacityFactor __attribute__((swift_name("capacityFactor")));
@property (readonly) MiamCoreBasketCompletion * _Nullable completion __attribute__((swift_name("completion")));
@property (readonly) MiamCoreBoolean * _Nullable confirmed __attribute__((swift_name("confirmed")));
@property (readonly) NSString * _Nullable name __attribute__((swift_name("name")));
@property (readonly) NSString * _Nullable token __attribute__((swift_name("token")));
@property (readonly) float totalPrice __attribute__((swift_name("totalPrice")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketAttributes.Companion")))
@interface MiamCoreBasketAttributesCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreBasketAttributesCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketCompletion")))
@interface MiamCoreBasketCompletion : MiamCoreBase
- (instancetype)initWithTotal:(MiamCoreInt * _Nullable)total found:(MiamCoreInt * _Nullable)found __attribute__((swift_name("init(total:found:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCoreBasketCompletionCompanion *companion __attribute__((swift_name("companion")));
- (MiamCoreInt * _Nullable)component1 __attribute__((swift_name("component1()")));
- (MiamCoreInt * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreBasketCompletion *)doCopyTotal:(MiamCoreInt * _Nullable)total found:(MiamCoreInt * _Nullable)found __attribute__((swift_name("doCopy(total:found:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreInt * _Nullable found __attribute__((swift_name("found")));
@property (readonly) MiamCoreInt * _Nullable total __attribute__((swift_name("total")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketCompletion.Companion")))
@interface MiamCoreBasketCompletionCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreBasketCompletionCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketEntriesItem")))
@interface MiamCoreBasketEntriesItem : MiamCoreBase
- (instancetype)initWithId:(MiamCoreInt * _Nullable)id itemId:(MiamCoreInt * _Nullable)itemId score:(MiamCoreFloat * _Nullable)score learningFactor:(MiamCoreInt * _Nullable)learningFactor timesSelected:(MiamCoreInt * _Nullable)timesSelected selected:(BOOL)selected default:(BOOL)default_ unitPrice:(MiamCoreDouble * _Nullable)unitPrice currency:(NSString * _Nullable)currency pftPlages:(NSArray<MiamCoreInt *> * _Nullable)pftPlages pftChecksum:(NSString * _Nullable)pftChecksum __attribute__((swift_name("init(id:itemId:score:learningFactor:timesSelected:selected:default:unitPrice:currency:pftPlages:pftChecksum:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCoreBasketEntriesItemCompanion *companion __attribute__((swift_name("companion")));
- (MiamCoreInt * _Nullable)component1 __attribute__((swift_name("component1()")));
- (NSArray<MiamCoreInt *> * _Nullable)component10 __attribute__((swift_name("component10()")));
- (NSString * _Nullable)component11 __attribute__((swift_name("component11()")));
- (MiamCoreInt * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreFloat * _Nullable)component3 __attribute__((swift_name("component3()")));
- (MiamCoreInt * _Nullable)component4 __attribute__((swift_name("component4()")));
- (MiamCoreInt * _Nullable)component5 __attribute__((swift_name("component5()")));
- (BOOL)component6 __attribute__((swift_name("component6()")));
- (BOOL)component7 __attribute__((swift_name("component7()")));
- (MiamCoreDouble * _Nullable)component8 __attribute__((swift_name("component8()")));
- (NSString * _Nullable)component9 __attribute__((swift_name("component9()")));
- (MiamCoreBasketEntriesItem *)doCopyId:(MiamCoreInt * _Nullable)id itemId:(MiamCoreInt * _Nullable)itemId score:(MiamCoreFloat * _Nullable)score learningFactor:(MiamCoreInt * _Nullable)learningFactor timesSelected:(MiamCoreInt * _Nullable)timesSelected selected:(BOOL)selected default:(BOOL)default_ unitPrice:(MiamCoreDouble * _Nullable)unitPrice currency:(NSString * _Nullable)currency pftPlages:(NSArray<MiamCoreInt *> * _Nullable)pftPlages pftChecksum:(NSString * _Nullable)pftChecksum __attribute__((swift_name("doCopy(id:itemId:score:learningFactor:timesSelected:selected:default:unitPrice:currency:pftPlages:pftChecksum:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable currency __attribute__((swift_name("currency")));
@property (readonly, getter=default) BOOL default_ __attribute__((swift_name("default_")));
@property (readonly) MiamCoreInt * _Nullable id __attribute__((swift_name("id")));
@property (readonly) MiamCoreInt * _Nullable itemId __attribute__((swift_name("itemId")));
@property (readonly) MiamCoreInt * _Nullable learningFactor __attribute__((swift_name("learningFactor")));
@property (readonly) NSString * _Nullable pftChecksum __attribute__((swift_name("pftChecksum")));
@property (readonly) NSArray<MiamCoreInt *> * _Nullable pftPlages __attribute__((swift_name("pftPlages")));
@property (readonly) MiamCoreFloat * _Nullable score __attribute__((swift_name("score")));
@property (readonly) BOOL selected __attribute__((swift_name("selected")));
@property (readonly) MiamCoreInt * _Nullable timesSelected __attribute__((swift_name("timesSelected")));
@property (readonly) MiamCoreDouble * _Nullable unitPrice __attribute__((swift_name("unitPrice")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketEntriesItem.Companion")))
@interface MiamCoreBasketEntriesItemCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreBasketEntriesItemCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((swift_name("BasketPreviewEntry")))
@protocol MiamCoreBasketPreviewEntry
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketEntry")))
@interface MiamCoreBasketEntry : MiamCoreRecord <MiamCoreBasketPreviewEntry>
- (instancetype)initWithId:(NSString *)id attributes:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)attributes json_relationships:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)json_relationships includedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("init(id:attributes:json_relationships:includedRecords:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreBasketEntryCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreBasketEntryAttributes * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreBasketEntryRelationships * _Nullable)component3 __attribute__((swift_name("component3()")));
- (MiamCoreBasketEntry *)doCopyId:(NSString *)id attributes:(MiamCoreBasketEntryAttributes * _Nullable)attributes relationships:(MiamCoreBasketEntryRelationships * _Nullable)relationships __attribute__((swift_name("doCopy(id:attributes:relationships:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
- (MiamCoreBasketEntry *)updateQuantityQty:(int32_t)qty __attribute__((swift_name("updateQuantity(qty:)")));
- (MiamCoreBasketEntry *)updateSelectedItemSelectedItemId:(int32_t)selectedItemId __attribute__((swift_name("updateSelectedItem(selectedItemId:)")));
- (MiamCoreBasketEntry *)updateStatusStatus:(NSString *)status __attribute__((swift_name("updateStatus(status:)")));
@property (readonly) MiamCoreBasketEntryAttributes * _Nullable attributes __attribute__((swift_name("attributes")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) int32_t itemsCountOrZero __attribute__((swift_name("itemsCountOrZero")));
@property BOOL needPatch __attribute__((swift_name("needPatch")));
@property (readonly) MiamCoreBasketEntryRelationships * _Nullable relationships __attribute__((swift_name("relationships")));
@property (readonly) MiamCoreItem * _Nullable selectedItem __attribute__((swift_name("selectedItem")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketEntry.Companion")))
@interface MiamCoreBasketEntryCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreBasketEntryCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketEntryAttributes")))
@interface MiamCoreBasketEntryAttributes : MiamCoreAttributes
- (instancetype)initWithSelectedItemId:(MiamCoreInt * _Nullable)selectedItemId learningFactor:(MiamCoreInt * _Nullable)learningFactor quantity:(MiamCoreInt * _Nullable)quantity recipeIds:(NSArray<MiamCoreInt *> * _Nullable)recipeIds groceriesEntryStatus:(NSString * _Nullable)groceriesEntryStatus basketEntriesItems:(NSArray<MiamCoreBasketEntriesItem *> * _Nullable)basketEntriesItems __attribute__((swift_name("init(selectedItemId:learningFactor:quantity:recipeIds:groceriesEntryStatus:basketEntriesItems:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreBasketEntryAttributesCompanion *companion __attribute__((swift_name("companion")));
- (MiamCoreInt * _Nullable)component1 __attribute__((swift_name("component1()")));
- (MiamCoreInt * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreInt * _Nullable)component3 __attribute__((swift_name("component3()")));
- (NSArray<MiamCoreInt *> * _Nullable)component4 __attribute__((swift_name("component4()")));
- (NSString * _Nullable)component5 __attribute__((swift_name("component5()")));
- (NSArray<MiamCoreBasketEntriesItem *> * _Nullable)component6 __attribute__((swift_name("component6()")));
- (MiamCoreBasketEntryAttributes *)doCopySelectedItemId:(MiamCoreInt * _Nullable)selectedItemId learningFactor:(MiamCoreInt * _Nullable)learningFactor quantity:(MiamCoreInt * _Nullable)quantity recipeIds:(NSArray<MiamCoreInt *> * _Nullable)recipeIds groceriesEntryStatus:(NSString * _Nullable)groceriesEntryStatus basketEntriesItems:(NSArray<MiamCoreBasketEntriesItem *> * _Nullable)basketEntriesItems __attribute__((swift_name("doCopy(selectedItemId:learningFactor:quantity:recipeIds:groceriesEntryStatus:basketEntriesItems:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property NSArray<MiamCoreBasketEntriesItem *> * _Nullable basketEntriesItems __attribute__((swift_name("basketEntriesItems")));
@property (readonly) NSString * _Nullable groceriesEntryStatus __attribute__((swift_name("groceriesEntryStatus")));
@property (readonly) MiamCoreInt * _Nullable learningFactor __attribute__((swift_name("learningFactor")));
@property (readonly) MiamCoreInt * _Nullable quantity __attribute__((swift_name("quantity")));
@property (readonly) NSArray<MiamCoreInt *> * _Nullable recipeIds __attribute__((swift_name("recipeIds")));
@property (readonly) MiamCoreInt * _Nullable selectedItemId __attribute__((swift_name("selectedItemId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketEntryAttributes.Companion")))
@interface MiamCoreBasketEntryAttributesCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreBasketEntryAttributesCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((swift_name("RelationshipList")))
@interface MiamCoreRelationshipList : MiamCoreBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreRelationshipListCompanion *companion __attribute__((swift_name("companion")));
- (NSArray<MiamCoreRecord *> *)buildedFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords subClass:(id<MiamCoreKotlinKClass>)subClass __attribute__((swift_name("buildedFromIncluded(includedRecords:subClass:)")));
- (void)serializeEncoder:(id<MiamCoreKotlinx_serialization_coreEncoder>)encoder __attribute__((swift_name("serialize(encoder:)")));
@property (readonly) NSArray<MiamCoreRecord *> *data __attribute__((swift_name("data")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketEntryRelationshipList")))
@interface MiamCoreBasketEntryRelationshipList : MiamCoreRelationshipList
- (instancetype)initWithData:(NSArray<MiamCoreBasketEntry *> *)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreBasketEntryRelationshipListCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
@property NSArray<MiamCoreBasketEntry *> *data __attribute__((swift_name("data")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketEntryRelationshipList.Companion")))
@interface MiamCoreBasketEntryRelationshipListCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreBasketEntryRelationshipListCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreSerializationStrategy")))
@protocol MiamCoreKotlinx_serialization_coreSerializationStrategy
@required
- (void)serializeEncoder:(id<MiamCoreKotlinx_serialization_coreEncoder>)encoder value:(id _Nullable)value __attribute__((swift_name("serialize(encoder:value:)")));
@property (readonly) id<MiamCoreKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreDeserializationStrategy")))
@protocol MiamCoreKotlinx_serialization_coreDeserializationStrategy
@required
- (id _Nullable)deserializeDecoder:(id<MiamCoreKotlinx_serialization_coreDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
@property (readonly) id<MiamCoreKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreKSerializer")))
@protocol MiamCoreKotlinx_serialization_coreKSerializer <MiamCoreKotlinx_serialization_coreSerializationStrategy, MiamCoreKotlinx_serialization_coreDeserializationStrategy>
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketEntryRelationshipListSerializer")))
@interface MiamCoreBasketEntryRelationshipListSerializer : MiamCoreBase <MiamCoreKotlinx_serialization_coreKSerializer>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)basketEntryRelationshipListSerializer __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreBasketEntryRelationshipListSerializer *shared __attribute__((swift_name("shared")));
- (MiamCoreBasketEntryRelationshipList *)deserializeDecoder:(id<MiamCoreKotlinx_serialization_coreDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (void)serializeEncoder:(id<MiamCoreKotlinx_serialization_coreEncoder>)encoder value:(MiamCoreBasketEntryRelationshipList *)value __attribute__((swift_name("serialize(encoder:value:)")));
@property (readonly) id<MiamCoreKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end;

__attribute__((swift_name("Relationships")))
@interface MiamCoreRelationships : MiamCoreBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreRelationshipsCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketEntryRelationships")))
@interface MiamCoreBasketEntryRelationships : MiamCoreRelationships
- (instancetype)initWithItems:(MiamCoreItemRelationshipList * _Nullable)items groceriesEntry:(MiamCoreGroceriesEntryRelationship * _Nullable)groceriesEntry __attribute__((swift_name("init(items:groceriesEntry:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreBasketEntryRelationshipsCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
- (MiamCoreItemRelationshipList * _Nullable)component1 __attribute__((swift_name("component1()")));
- (MiamCoreGroceriesEntryRelationship * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreBasketEntryRelationships *)doCopyItems:(MiamCoreItemRelationshipList * _Nullable)items groceriesEntry:(MiamCoreGroceriesEntryRelationship * _Nullable)groceriesEntry __attribute__((swift_name("doCopy(items:groceriesEntry:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property MiamCoreGroceriesEntryRelationship * _Nullable groceriesEntry __attribute__((swift_name("groceriesEntry")));
@property MiamCoreItemRelationshipList * _Nullable items __attribute__((swift_name("items")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketEntryRelationships.Companion")))
@interface MiamCoreBasketEntryRelationshipsCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreBasketEntryRelationshipsCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketPreviewLine")))
@interface MiamCoreBasketPreviewLine : MiamCoreBase
- (instancetype)initWithId:(NSString * _Nullable)id record:(id<MiamCoreBasketPreviewEntry>)record isRecipe:(BOOL)isRecipe inlineTag:(NSString * _Nullable)inlineTag title:(NSString *)title picture:(NSString *)picture bplDescription:(NSArray<NSString *> *)bplDescription price:(NSString *)price count:(int32_t)count entries:(MiamCoreLineEntries * _Nullable)entries _displayMode:(BOOL)_displayMode __attribute__((swift_name("init(id:record:isRecipe:inlineTag:title:picture:bplDescription:price:count:entries:_displayMode:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCoreBasketPreviewLineCompanion *companion __attribute__((swift_name("companion")));
- (NSString * _Nullable)component1 __attribute__((swift_name("component1()")));
- (MiamCoreLineEntries * _Nullable)component10 __attribute__((swift_name("component10()")));
- (BOOL)component11 __attribute__((swift_name("component11()")));
- (id<MiamCoreBasketPreviewEntry>)component2 __attribute__((swift_name("component2()")));
- (BOOL)component3 __attribute__((swift_name("component3()")));
- (NSString * _Nullable)component4 __attribute__((swift_name("component4()")));
- (NSString *)component5 __attribute__((swift_name("component5()")));
- (NSString *)component6 __attribute__((swift_name("component6()")));
- (NSArray<NSString *> *)component7 __attribute__((swift_name("component7()")));
- (NSString *)component8 __attribute__((swift_name("component8()")));
- (int32_t)component9 __attribute__((swift_name("component9()")));
- (MiamCoreBasketPreviewLine *)doCopyId:(NSString * _Nullable)id record:(id<MiamCoreBasketPreviewEntry>)record isRecipe:(BOOL)isRecipe inlineTag:(NSString * _Nullable)inlineTag title:(NSString *)title picture:(NSString *)picture bplDescription:(NSArray<NSString *> *)bplDescription price:(NSString *)price count:(int32_t)count entries:(MiamCoreLineEntries * _Nullable)entries _displayMode:(BOOL)_displayMode __attribute__((swift_name("doCopy(id:record:isRecipe:inlineTag:title:picture:bplDescription:price:count:entries:_displayMode:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (BOOL)hasEntries __attribute__((swift_name("hasEntries()")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL _displayMode __attribute__((swift_name("_displayMode")));
@property (readonly) NSArray<NSString *> *bplDescription __attribute__((swift_name("bplDescription")));
@property (readonly) int32_t count __attribute__((swift_name("count")));
@property (readonly) MiamCoreLineEntries * _Nullable entries __attribute__((swift_name("entries")));
@property (readonly) NSString * _Nullable id __attribute__((swift_name("id")));
@property (readonly) NSString * _Nullable inlineTag __attribute__((swift_name("inlineTag")));
@property (readonly) BOOL isRecipe __attribute__((swift_name("isRecipe")));
@property (readonly) NSString *picture __attribute__((swift_name("picture")));
@property (readonly) NSString *price __attribute__((swift_name("price")));
@property (readonly) id<MiamCoreBasketPreviewEntry> record __attribute__((swift_name("record")));
@property (readonly) NSString *title __attribute__((swift_name("title")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketPreviewLine.Companion")))
@interface MiamCoreBasketPreviewLineCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreBasketPreviewLineCompanion *shared __attribute__((swift_name("shared")));
- (MiamCoreBasketPreviewLine *)fromBasketEntryEntry:(MiamCoreBasketEntry *)entry __attribute__((swift_name("fromBasketEntry(entry:)")));
- (MiamCoreBasketPreviewLine *)fromBasketEntryItemEntry:(MiamCoreBasketEntry *)entry item:(MiamCoreItem *)item __attribute__((swift_name("fromBasketEntryItem(entry:item:)")));
- (MiamCoreBasketPreviewLine *)fromRecipeRecipe:(MiamCoreRecipe *)recipe itemsCount:(int32_t)itemsCount pricePerGuest:(MiamCoreDouble * _Nullable)pricePerGuest guestNum:(MiamCoreInt * _Nullable)guestNum recipePrice:(NSString * _Nullable)recipePrice entries:(MiamCoreLineEntries * _Nullable)entries __attribute__((swift_name("fromRecipe(recipe:itemsCount:pricePerGuest:guestNum:recipePrice:entries:)")));
- (NSArray<MiamCoreBasketPreviewLine *> *)recipesAndLineEntriesToBasketPreviewLineGroceriesList:(MiamCoreGroceriesList *)groceriesList lineEntries:(NSArray<MiamCoreLineEntries *> *)lineEntries __attribute__((swift_name("recipesAndLineEntriesToBasketPreviewLine(groceriesList:lineEntries:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketRelationships")))
@interface MiamCoreBasketRelationships : MiamCoreRelationships
- (instancetype)initWithBasketEntries:(MiamCoreBasketEntryRelationshipList * _Nullable)basketEntries __attribute__((swift_name("init(basketEntries:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreBasketRelationshipsCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
- (MiamCoreBasketEntryRelationshipList * _Nullable)component1 __attribute__((swift_name("component1()")));
- (MiamCoreBasketRelationships *)doCopyBasketEntries:(MiamCoreBasketEntryRelationshipList * _Nullable)basketEntries __attribute__((swift_name("doCopy(basketEntries:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property MiamCoreBasketEntryRelationshipList * _Nullable basketEntries __attribute__((swift_name("basketEntries")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketRelationships.Companion")))
@interface MiamCoreBasketRelationshipsCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreBasketRelationshipsCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CatalogFilterOptions")))
@interface MiamCoreCatalogFilterOptions : MiamCoreBase
- (instancetype)initWithName:(NSString *)name uiLabel:(NSString *)uiLabel isSelected:(BOOL)isSelected __attribute__((swift_name("init(name:uiLabel:isSelected:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (BOOL)component3 __attribute__((swift_name("component3()")));
- (MiamCoreCatalogFilterOptions *)doCopyName:(NSString *)name uiLabel:(NSString *)uiLabel isSelected:(BOOL)isSelected __attribute__((swift_name("doCopy(name:uiLabel:isSelected:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (MiamCoreCatalogFilterOptions *)off __attribute__((swift_name("off()")));
- (MiamCoreCatalogFilterOptions *)on __attribute__((swift_name("on()")));
- (NSString *)description __attribute__((swift_name("description()")));
- (MiamCoreCatalogFilterOptions *)toogle __attribute__((swift_name("toogle()")));
@property BOOL isSelected __attribute__((swift_name("isSelected")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) NSString *uiLabel __attribute__((swift_name("uiLabel")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CheckableTag")))
@interface MiamCoreCheckableTag : MiamCoreBase
- (instancetype)initWithTagType:(MiamCoreTagTypes *)tagType tag:(MiamCoreTag *)tag __attribute__((swift_name("init(tagType:tag:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithTagType:(MiamCoreTagTypes *)tagType tag:(MiamCoreTag *)tag isChecked:(BOOL)isChecked __attribute__((swift_name("init(tagType:tag:isChecked:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCoreCheckableTagCompanion *companion __attribute__((swift_name("companion")));
- (MiamCoreTagTypes *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreTag *)component2 __attribute__((swift_name("component2()")));
- (BOOL)component3 __attribute__((swift_name("component3()")));
- (MiamCoreCheckableTag *)doCopyTagType:(MiamCoreTagTypes *)tagType tag:(MiamCoreTag *)tag isChecked:(BOOL)isChecked __attribute__((swift_name("doCopy(tagType:tag:isChecked:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (MiamCoreCheckableTag *)resetWithStorageTagIds:(NSArray<NSString *> *)storageTagIds __attribute__((swift_name("resetWith(storageTagIds:)")));
- (NSString *)description __attribute__((swift_name("description()")));
- (MiamCoreCheckableTag *)toggleIfNeededTagIdToToggle:(NSString *)tagIdToToggle __attribute__((swift_name("toggleIfNeeded(tagIdToToggle:)")));
@property (readonly) BOOL changedFromItsDefaultValue __attribute__((swift_name("changedFromItsDefaultValue")));
@property (readonly) BOOL isChecked __attribute__((swift_name("isChecked")));
@property (readonly) BOOL isIncludedInQuery __attribute__((swift_name("isIncludedInQuery")));
@property (readonly) MiamCoreTag *tag __attribute__((swift_name("tag")));
@property (readonly) MiamCoreTagTypes *tagType __attribute__((swift_name("tagType")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CheckableTag.Companion")))
@interface MiamCoreCheckableTagCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreCheckableTagCompanion *shared __attribute__((swift_name("shared")));
- (BOOL)checkedByDefaultTagType:(MiamCoreTagTypes *)tagType __attribute__((swift_name("checkedByDefault(tagType:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesEntry")))
@interface MiamCoreGroceriesEntry : MiamCoreRecord
- (instancetype)initWithId:(NSString *)id attributes:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)attributes json_relationships:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)json_relationships includedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("init(id:attributes:json_relationships:includedRecords:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreGroceriesEntryCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreGroceriesEntryAttributes * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreGroceriesEntryRelationships * _Nullable)component3 __attribute__((swift_name("component3()")));
- (MiamCoreGroceriesEntry *)doCopyId:(NSString *)id attributes:(MiamCoreGroceriesEntryAttributes * _Nullable)attributes relationships:(MiamCoreGroceriesEntryRelationships * _Nullable)relationships __attribute__((swift_name("doCopy(id:attributes:relationships:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
- (MiamCoreGroceriesEntry *)updateStatusStatus:(NSString *)status __attribute__((swift_name("updateStatus(status:)")));
@property (readonly) MiamCoreGroceriesEntryAttributes * _Nullable attributes __attribute__((swift_name("attributes")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property BOOL needPatch __attribute__((swift_name("needPatch")));
@property (readonly) MiamCoreGroceriesEntryRelationships * _Nullable relationships __attribute__((swift_name("relationships")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesEntry.Companion")))
@interface MiamCoreGroceriesEntryCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreGroceriesEntryCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesEntryAttributes")))
@interface MiamCoreGroceriesEntryAttributes : MiamCoreAttributes
- (instancetype)initWithName:(NSString * _Nullable)name capacityVolume:(NSString * _Nullable)capacityVolume capacityUnit:(NSString * _Nullable)capacityUnit capacityFactor:(MiamCoreInt * _Nullable)capacityFactor status:(NSString * _Nullable)status recipeIds:(NSArray<MiamCoreInt *> * _Nullable)recipeIds __attribute__((swift_name("init(name:capacityVolume:capacityUnit:capacityFactor:status:recipeIds:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreGroceriesEntryAttributesCompanion *companion __attribute__((swift_name("companion")));
- (NSString * _Nullable)component1 __attribute__((swift_name("component1()")));
- (NSString * _Nullable)component2 __attribute__((swift_name("component2()")));
- (NSString * _Nullable)component3 __attribute__((swift_name("component3()")));
- (MiamCoreInt * _Nullable)component4 __attribute__((swift_name("component4()")));
- (NSString * _Nullable)component5 __attribute__((swift_name("component5()")));
- (NSArray<MiamCoreInt *> * _Nullable)component6 __attribute__((swift_name("component6()")));
- (MiamCoreGroceriesEntryAttributes *)doCopyName:(NSString * _Nullable)name capacityVolume:(NSString * _Nullable)capacityVolume capacityUnit:(NSString * _Nullable)capacityUnit capacityFactor:(MiamCoreInt * _Nullable)capacityFactor status:(NSString * _Nullable)status recipeIds:(NSArray<MiamCoreInt *> * _Nullable)recipeIds __attribute__((swift_name("doCopy(name:capacityVolume:capacityUnit:capacityFactor:status:recipeIds:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreInt * _Nullable capacityFactor __attribute__((swift_name("capacityFactor")));
@property (readonly) NSString * _Nullable capacityUnit __attribute__((swift_name("capacityUnit")));
@property (readonly) NSString * _Nullable capacityVolume __attribute__((swift_name("capacityVolume")));
@property (readonly) NSString * _Nullable name __attribute__((swift_name("name")));
@property (readonly) NSArray<MiamCoreInt *> * _Nullable recipeIds __attribute__((swift_name("recipeIds")));
@property (readonly) NSString * _Nullable status __attribute__((swift_name("status")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesEntryAttributes.Companion")))
@interface MiamCoreGroceriesEntryAttributesCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreGroceriesEntryAttributesCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((swift_name("Relationship")))
@interface MiamCoreRelationship : MiamCoreBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreRelationshipCompanion *companion __attribute__((swift_name("companion")));
- (MiamCoreRecord *)buildedFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords subClass:(id<MiamCoreKotlinKClass>)subClass __attribute__((swift_name("buildedFromIncluded(includedRecords:subClass:)")));
- (void)serializeEncoder:(id<MiamCoreKotlinx_serialization_coreEncoder>)encoder __attribute__((swift_name("serialize(encoder:)")));
@property (readonly) MiamCoreRecord *data __attribute__((swift_name("data")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesEntryRelationship")))
@interface MiamCoreGroceriesEntryRelationship : MiamCoreRelationship
- (instancetype)initWithData:(MiamCoreGroceriesEntry *)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreGroceriesEntryRelationshipCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
@property MiamCoreGroceriesEntry *data __attribute__((swift_name("data")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesEntryRelationship.Companion")))
@interface MiamCoreGroceriesEntryRelationshipCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreGroceriesEntryRelationshipCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesEntryRelationshipList")))
@interface MiamCoreGroceriesEntryRelationshipList : MiamCoreRelationshipList
- (instancetype)initWithData:(NSArray<MiamCoreGroceriesEntry *> *)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreGroceriesEntryRelationshipListCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
@property NSArray<MiamCoreGroceriesEntry *> *data __attribute__((swift_name("data")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesEntryRelationshipList.Companion")))
@interface MiamCoreGroceriesEntryRelationshipListCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreGroceriesEntryRelationshipListCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesEntryRelationshipListSerializer")))
@interface MiamCoreGroceriesEntryRelationshipListSerializer : MiamCoreBase <MiamCoreKotlinx_serialization_coreKSerializer>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)groceriesEntryRelationshipListSerializer __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreGroceriesEntryRelationshipListSerializer *shared __attribute__((swift_name("shared")));
- (MiamCoreGroceriesEntryRelationshipList *)deserializeDecoder:(id<MiamCoreKotlinx_serialization_coreDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (void)serializeEncoder:(id<MiamCoreKotlinx_serialization_coreEncoder>)encoder value:(MiamCoreGroceriesEntryRelationshipList *)value __attribute__((swift_name("serialize(encoder:value:)")));
@property (readonly) id<MiamCoreKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesEntryRelationshipSerializer")))
@interface MiamCoreGroceriesEntryRelationshipSerializer : MiamCoreBase <MiamCoreKotlinx_serialization_coreKSerializer>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)groceriesEntryRelationshipSerializer __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreGroceriesEntryRelationshipSerializer *shared __attribute__((swift_name("shared")));
- (MiamCoreGroceriesEntryRelationship *)deserializeDecoder:(id<MiamCoreKotlinx_serialization_coreDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (void)serializeEncoder:(id<MiamCoreKotlinx_serialization_coreEncoder>)encoder value:(MiamCoreGroceriesEntryRelationship *)value __attribute__((swift_name("serialize(encoder:value:)")));
@property (readonly) id<MiamCoreKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesEntryRelationships")))
@interface MiamCoreGroceriesEntryRelationships : MiamCoreRelationships
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreGroceriesEntryRelationshipsCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesEntryRelationships.Companion")))
@interface MiamCoreGroceriesEntryRelationshipsCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreGroceriesEntryRelationshipsCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesList")))
@interface MiamCoreGroceriesList : MiamCoreRecord
- (instancetype)initWithId:(NSString *)id attributes:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)attributes json_relationships:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)json_relationships includedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("init(id:attributes:json_relationships:includedRecords:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreGroceriesListCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreGroceriesListAttributes * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreGroceriesListRelationships * _Nullable)component3 __attribute__((swift_name("component3()")));
- (NSArray<MiamCoreRecipe *> *)component4 __attribute__((swift_name("component4()")));
- (MiamCoreGroceriesList *)doCopyId:(NSString *)id attributes:(MiamCoreGroceriesListAttributes * _Nullable)attributes relationships:(MiamCoreGroceriesListRelationships * _Nullable)relationships recipes:(NSArray<MiamCoreRecipe *> *)recipes __attribute__((swift_name("doCopy(id:attributes:relationships:recipes:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (int32_t)guestsForRecipeRecipeId:(NSString *)recipeId __attribute__((swift_name("guestsForRecipe(recipeId:)")));
- (BOOL)hasRecipeRecipeId:(NSString *)recipeId __attribute__((swift_name("hasRecipe(recipeId:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSArray<NSString *> *)missingRecipesIdsExistingRecipes:(NSArray<MiamCoreRecipe *> *)existingRecipes __attribute__((swift_name("missingRecipesIds(existingRecipes:)")));
- (void)rebuildRecipesRelationshipsMissingRecipes:(NSArray<MiamCoreRecipe *> *)missingRecipes existingRecipes:(NSArray<MiamCoreRecipe *> *)existingRecipes __attribute__((swift_name("rebuildRecipesRelationships(missingRecipes:existingRecipes:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property MiamCoreGroceriesListAttributes * _Nullable attributes __attribute__((swift_name("attributes")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property NSArray<MiamCoreRecipe *> *recipes __attribute__((swift_name("recipes")));
@property (readonly) MiamCoreGroceriesListRelationships * _Nullable relationships __attribute__((swift_name("relationships")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesList.Companion")))
@interface MiamCoreGroceriesListCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreGroceriesListCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesListAttributes")))
@interface MiamCoreGroceriesListAttributes : MiamCoreAttributes
- (instancetype)initWithName:(NSString *)name createdAt:(NSString *)createdAt updatedAt:(NSString *)updatedAt recipesInfos:(NSMutableArray<MiamCoreRecipeInfos *> *)recipesInfos userId:(NSString * _Nullable)userId appendRecipes:(BOOL)appendRecipes __attribute__((swift_name("init(name:createdAt:updatedAt:recipesInfos:userId:appendRecipes:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreGroceriesListAttributesCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (NSMutableArray<MiamCoreRecipeInfos *> *)component4 __attribute__((swift_name("component4()")));
- (NSString * _Nullable)component5 __attribute__((swift_name("component5()")));
- (BOOL)component6 __attribute__((swift_name("component6()")));
- (MiamCoreGroceriesListAttributes *)doCopyName:(NSString *)name createdAt:(NSString *)createdAt updatedAt:(NSString *)updatedAt recipesInfos:(NSMutableArray<MiamCoreRecipeInfos *> *)recipesInfos userId:(NSString * _Nullable)userId appendRecipes:(BOOL)appendRecipes __attribute__((swift_name("doCopy(name:createdAt:updatedAt:recipesInfos:userId:appendRecipes:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property BOOL appendRecipes __attribute__((swift_name("appendRecipes")));
@property (readonly) NSString *createdAt __attribute__((swift_name("createdAt")));
@property NSString *name __attribute__((swift_name("name")));
@property (readonly) NSMutableArray<MiamCoreRecipeInfos *> *recipesInfos __attribute__((swift_name("recipesInfos")));
@property (readonly) NSString *updatedAt __attribute__((swift_name("updatedAt")));
@property (readonly) NSString * _Nullable userId __attribute__((swift_name("userId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesListAttributes.Companion")))
@interface MiamCoreGroceriesListAttributesCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreGroceriesListAttributesCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesListRelationships")))
@interface MiamCoreGroceriesListRelationships : MiamCoreRelationships
- (instancetype)initWithGroceriesEntries:(MiamCoreGroceriesEntryRelationshipList * _Nullable)groceriesEntries __attribute__((swift_name("init(groceriesEntries:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreGroceriesListRelationshipsCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
- (MiamCoreGroceriesEntryRelationshipList * _Nullable)component1 __attribute__((swift_name("component1()")));
- (MiamCoreGroceriesListRelationships *)doCopyGroceriesEntries:(MiamCoreGroceriesEntryRelationshipList * _Nullable)groceriesEntries __attribute__((swift_name("doCopy(groceriesEntries:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property MiamCoreGroceriesEntryRelationshipList * _Nullable groceriesEntries __attribute__((swift_name("groceriesEntries")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesListRelationships.Companion")))
@interface MiamCoreGroceriesListRelationshipsCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreGroceriesListRelationshipsCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ingredient")))
@interface MiamCoreIngredient : MiamCoreRecord
- (instancetype)initWithId:(NSString *)id attributes:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)attributes json_relationships:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)json_relationships includedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("init(id:attributes:json_relationships:includedRecords:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreIngredientCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreIngredientAttributes * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreIngredientRelationships * _Nullable)component3 __attribute__((swift_name("component3()")));
- (MiamCoreIngredient *)doCopyId:(NSString *)id attributes:(MiamCoreIngredientAttributes * _Nullable)attributes relationships:(MiamCoreIngredientRelationships * _Nullable)relationships __attribute__((swift_name("doCopy(id:attributes:relationships:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreIngredientAttributes * _Nullable attributes __attribute__((swift_name("attributes")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) MiamCoreIngredientRelationships * _Nullable relationships __attribute__((swift_name("relationships")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ingredient.Companion")))
@interface MiamCoreIngredientCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreIngredientCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IngredientAttributes")))
@interface MiamCoreIngredientAttributes : MiamCoreAttributes
- (instancetype)initWithName:(NSString * _Nullable)name quantity:(NSString * _Nullable)quantity unit:(NSString * _Nullable)unit active:(BOOL)active pictureUrl:(NSString * _Nullable)pictureUrl forcedEans:(NSArray<NSString *> * _Nullable)forcedEans __attribute__((swift_name("init(name:quantity:unit:active:pictureUrl:forcedEans:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreIngredientAttributesCompanion *companion __attribute__((swift_name("companion")));
- (NSString * _Nullable)component1 __attribute__((swift_name("component1()")));
- (NSString * _Nullable)component2 __attribute__((swift_name("component2()")));
- (NSString * _Nullable)component3 __attribute__((swift_name("component3()")));
- (BOOL)component4 __attribute__((swift_name("component4()")));
- (NSString * _Nullable)component5 __attribute__((swift_name("component5()")));
- (NSArray<NSString *> * _Nullable)component6 __attribute__((swift_name("component6()")));
- (MiamCoreIngredientAttributes *)doCopyName:(NSString * _Nullable)name quantity:(NSString * _Nullable)quantity unit:(NSString * _Nullable)unit active:(BOOL)active pictureUrl:(NSString * _Nullable)pictureUrl forcedEans:(NSArray<NSString *> * _Nullable)forcedEans __attribute__((swift_name("doCopy(name:quantity:unit:active:pictureUrl:forcedEans:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL active __attribute__((swift_name("active")));
@property (readonly) NSArray<NSString *> * _Nullable forcedEans __attribute__((swift_name("forcedEans")));
@property (readonly) NSString * _Nullable name __attribute__((swift_name("name")));
@property (readonly) NSString * _Nullable pictureUrl __attribute__((swift_name("pictureUrl")));
@property (readonly) NSString * _Nullable quantity __attribute__((swift_name("quantity")));
@property (readonly) NSString * _Nullable unit __attribute__((swift_name("unit")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IngredientAttributes.Companion")))
@interface MiamCoreIngredientAttributesCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreIngredientAttributesCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IngredientListRelationship")))
@interface MiamCoreIngredientListRelationship : MiamCoreRelationshipList
- (instancetype)initWithData:(NSArray<MiamCoreIngredient *> *)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreIngredientListRelationshipCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
@property NSArray<MiamCoreIngredient *> *data __attribute__((swift_name("data")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IngredientListRelationship.Companion")))
@interface MiamCoreIngredientListRelationshipCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreIngredientListRelationshipCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IngredientListSerializer")))
@interface MiamCoreIngredientListSerializer : MiamCoreBase <MiamCoreKotlinx_serialization_coreKSerializer>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)ingredientListSerializer __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreIngredientListSerializer *shared __attribute__((swift_name("shared")));
- (MiamCoreIngredientListRelationship *)deserializeDecoder:(id<MiamCoreKotlinx_serialization_coreDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (void)serializeEncoder:(id<MiamCoreKotlinx_serialization_coreEncoder>)encoder value:(MiamCoreIngredientListRelationship *)value __attribute__((swift_name("serialize(encoder:value:)")));
@property (readonly) id<MiamCoreKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IngredientRelationships")))
@interface MiamCoreIngredientRelationships : MiamCoreRelationships
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreIngredientRelationshipsCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IngredientRelationships.Companion")))
@interface MiamCoreIngredientRelationshipsCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreIngredientRelationshipsCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Item")))
@interface MiamCoreItem : MiamCoreRecord
- (instancetype)initWithId:(NSString *)id attributes:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)attributes json_relationships:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)json_relationships includedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("init(id:attributes:json_relationships:includedRecords:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreItemCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreItemAttributes * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreItemRelationships * _Nullable)component3 __attribute__((swift_name("component3()")));
- (MiamCoreItem *)doCopyId:(NSString *)id attributes:(MiamCoreItemAttributes * _Nullable)attributes relationships:(MiamCoreItemRelationships * _Nullable)relationships __attribute__((swift_name("doCopy(id:attributes:relationships:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreItemAttributes * _Nullable attributes __attribute__((swift_name("attributes")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) MiamCoreItemRelationships * _Nullable relationships __attribute__((swift_name("relationships")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Item.Companion")))
@interface MiamCoreItemCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreItemCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ItemAttributes")))
@interface MiamCoreItemAttributes : MiamCoreAttributes
- (instancetype)initWithExtId:(NSString * _Nullable)extId name:(NSString *)name status:(NSString * _Nullable)status itemDescription:(NSString * _Nullable)itemDescription brand:(NSString * _Nullable)brand productPage:(NSString * _Nullable)productPage image:(NSString * _Nullable)image unitPrice:(NSString *)unitPrice capacityUnit:(NSString * _Nullable)capacityUnit capacityVolume:(NSString * _Nullable)capacityVolume capacityFactor:(MiamCoreInt * _Nullable)capacityFactor createdAt:(NSString *)createdAt updatedAt:(NSString *)updatedAt promoted:(BOOL)promoted variableCapacity:(BOOL)variableCapacity __attribute__((swift_name("init(extId:name:status:itemDescription:brand:productPage:image:unitPrice:capacityUnit:capacityVolume:capacityFactor:createdAt:updatedAt:promoted:variableCapacity:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreItemAttributesCompanion *companion __attribute__((swift_name("companion")));
- (NSString * _Nullable)component1 __attribute__((swift_name("component1()")));
- (NSString * _Nullable)component10 __attribute__((swift_name("component10()")));
- (MiamCoreInt * _Nullable)component11 __attribute__((swift_name("component11()")));
- (NSString *)component12 __attribute__((swift_name("component12()")));
- (NSString *)component13 __attribute__((swift_name("component13()")));
- (BOOL)component14 __attribute__((swift_name("component14()")));
- (BOOL)component15 __attribute__((swift_name("component15()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString * _Nullable)component3 __attribute__((swift_name("component3()")));
- (NSString * _Nullable)component4 __attribute__((swift_name("component4()")));
- (NSString * _Nullable)component5 __attribute__((swift_name("component5()")));
- (NSString * _Nullable)component6 __attribute__((swift_name("component6()")));
- (NSString * _Nullable)component7 __attribute__((swift_name("component7()")));
- (NSString *)component8 __attribute__((swift_name("component8()")));
- (NSString * _Nullable)component9 __attribute__((swift_name("component9()")));
- (MiamCoreItemAttributes *)doCopyExtId:(NSString * _Nullable)extId name:(NSString *)name status:(NSString * _Nullable)status itemDescription:(NSString * _Nullable)itemDescription brand:(NSString * _Nullable)brand productPage:(NSString * _Nullable)productPage image:(NSString * _Nullable)image unitPrice:(NSString *)unitPrice capacityUnit:(NSString * _Nullable)capacityUnit capacityVolume:(NSString * _Nullable)capacityVolume capacityFactor:(MiamCoreInt * _Nullable)capacityFactor createdAt:(NSString *)createdAt updatedAt:(NSString *)updatedAt promoted:(BOOL)promoted variableCapacity:(BOOL)variableCapacity __attribute__((swift_name("doCopy(extId:name:status:itemDescription:brand:productPage:image:unitPrice:capacityUnit:capacityVolume:capacityFactor:createdAt:updatedAt:promoted:variableCapacity:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable brand __attribute__((swift_name("brand")));
@property (readonly) MiamCoreInt * _Nullable capacityFactor __attribute__((swift_name("capacityFactor")));
@property (readonly) NSString * _Nullable capacityUnit __attribute__((swift_name("capacityUnit")));
@property (readonly) NSString * _Nullable capacityVolume __attribute__((swift_name("capacityVolume")));
@property (readonly) NSString *createdAt __attribute__((swift_name("createdAt")));
@property (readonly) NSString * _Nullable extId __attribute__((swift_name("extId")));
@property (readonly) NSString * _Nullable image __attribute__((swift_name("image")));
@property (readonly) NSString * _Nullable itemDescription __attribute__((swift_name("itemDescription")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) NSString * _Nullable productPage __attribute__((swift_name("productPage")));
@property (readonly) BOOL promoted __attribute__((swift_name("promoted")));
@property (readonly) NSString * _Nullable status __attribute__((swift_name("status")));
@property (readonly) NSString *unitPrice __attribute__((swift_name("unitPrice")));
@property (readonly) NSString *updatedAt __attribute__((swift_name("updatedAt")));
@property (readonly) BOOL variableCapacity __attribute__((swift_name("variableCapacity")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ItemAttributes.Companion")))
@interface MiamCoreItemAttributesCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreItemAttributesCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ItemRelationshipList")))
@interface MiamCoreItemRelationshipList : MiamCoreRelationshipList
- (instancetype)initWithData:(NSArray<MiamCoreItem *> *)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreItemRelationshipListCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
@property NSArray<MiamCoreItem *> *data __attribute__((swift_name("data")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ItemRelationshipList.Companion")))
@interface MiamCoreItemRelationshipListCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreItemRelationshipListCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ItemRelationshipListSerializer")))
@interface MiamCoreItemRelationshipListSerializer : MiamCoreBase <MiamCoreKotlinx_serialization_coreKSerializer>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)itemRelationshipListSerializer __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreItemRelationshipListSerializer *shared __attribute__((swift_name("shared")));
- (MiamCoreItemRelationshipList *)deserializeDecoder:(id<MiamCoreKotlinx_serialization_coreDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (void)serializeEncoder:(id<MiamCoreKotlinx_serialization_coreEncoder>)encoder value:(MiamCoreItemRelationshipList *)value __attribute__((swift_name("serialize(encoder:value:)")));
@property (readonly) id<MiamCoreKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ItemRelationships")))
@interface MiamCoreItemRelationships : MiamCoreRelationships
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreItemRelationshipsCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ItemRelationships.Companion")))
@interface MiamCoreItemRelationshipsCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreItemRelationshipsCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("LineEntries")))
@interface MiamCoreLineEntries : MiamCoreBase
- (instancetype)initWithFound:(NSArray<MiamCoreBasketEntry *> *)found notFound:(NSArray<MiamCoreBasketEntry *> *)notFound oftenDeleted:(NSArray<MiamCoreBasketEntry *> *)oftenDeleted removed:(NSArray<MiamCoreBasketEntry *> *)removed __attribute__((swift_name("init(found:notFound:oftenDeleted:removed:)"))) __attribute__((objc_designated_initializer));
- (NSArray<MiamCoreBasketEntry *> *)component1 __attribute__((swift_name("component1()")));
- (NSArray<MiamCoreBasketEntry *> *)component2 __attribute__((swift_name("component2()")));
- (NSArray<MiamCoreBasketEntry *> *)component3 __attribute__((swift_name("component3()")));
- (NSArray<MiamCoreBasketEntry *> *)component4 __attribute__((swift_name("component4()")));
- (MiamCoreLineEntries *)doCopyFound:(NSArray<MiamCoreBasketEntry *> *)found notFound:(NSArray<MiamCoreBasketEntry *> *)notFound oftenDeleted:(NSArray<MiamCoreBasketEntry *> *)oftenDeleted removed:(NSArray<MiamCoreBasketEntry *> *)removed __attribute__((swift_name("doCopy(found:notFound:oftenDeleted:removed:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
- (double)totalPrice __attribute__((swift_name("totalPrice()")));
- (MiamCoreLineEntries *)updateBasketEntriesBasketEntries:(NSArray<MiamCoreBasketEntry *> *)basketEntries __attribute__((swift_name("updateBasketEntries(basketEntries:)")));
@property (readonly) NSArray<MiamCoreBasketEntry *> *found __attribute__((swift_name("found")));
@property (readonly) NSArray<MiamCoreBasketEntry *> *notFound __attribute__((swift_name("notFound")));
@property (readonly) NSArray<MiamCoreBasketEntry *> *oftenDeleted __attribute__((swift_name("oftenDeleted")));
@property (readonly) NSArray<MiamCoreBasketEntry *> *removed __attribute__((swift_name("removed")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Package")))
@interface MiamCorePackage : MiamCoreRecord
- (instancetype)initWithId:(NSString *)id attributes:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)attributes json_relationships:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)json_relationships includedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("init(id:attributes:json_relationships:includedRecords:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCorePackageCompanion *companion __attribute__((swift_name("companion")));
- (MiamCorePackage *)buildRecipesRecipes:(NSArray<MiamCoreRecipe *> *)recipes __attribute__((swift_name("buildRecipes(recipes:)")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCorePackageAttributes * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCorePackageRelationships * _Nullable)component3 __attribute__((swift_name("component3()")));
- (MiamCorePackage *)doCopyId:(NSString *)id attributes:(MiamCorePackageAttributes * _Nullable)attributes relationships:(MiamCorePackageRelationships * _Nullable)relationships __attribute__((swift_name("doCopy(id:attributes:relationships:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property MiamCorePackageAttributes * _Nullable attributes __attribute__((swift_name("attributes")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) MiamCorePackageRelationships * _Nullable relationships __attribute__((swift_name("relationships")));
@property (readonly) NSString * _Nullable subtitle __attribute__((swift_name("subtitle")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Package.Companion")))
@interface MiamCorePackageCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCorePackageCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PackageAttributes")))
@interface MiamCorePackageAttributes : MiamCoreAttributes
- (instancetype)initWithTitle:(NSString *)title authorId:(NSString * _Nullable)authorId editable:(MiamCoreBoolean * _Nullable)editable shared:(MiamCoreBoolean * _Nullable)shared catalogCategory:(MiamCoreBoolean * _Nullable)catalogCategory catalogPosition:(MiamCoreInt * _Nullable)catalogPosition recipesCount:(int32_t)recipesCount settings:(MiamCorePackageSettings *)settings __attribute__((swift_name("init(title:authorId:editable:shared:catalogCategory:catalogPosition:recipesCount:settings:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCorePackageAttributesCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreBoolean * _Nullable)component3 __attribute__((swift_name("component3()")));
- (MiamCoreBoolean * _Nullable)component4 __attribute__((swift_name("component4()")));
- (MiamCoreBoolean * _Nullable)component5 __attribute__((swift_name("component5()")));
- (MiamCoreInt * _Nullable)component6 __attribute__((swift_name("component6()")));
- (int32_t)component7 __attribute__((swift_name("component7()")));
- (MiamCorePackageSettings *)component8 __attribute__((swift_name("component8()")));
- (MiamCorePackageAttributes *)doCopyTitle:(NSString *)title authorId:(NSString * _Nullable)authorId editable:(MiamCoreBoolean * _Nullable)editable shared:(MiamCoreBoolean * _Nullable)shared catalogCategory:(MiamCoreBoolean * _Nullable)catalogCategory catalogPosition:(MiamCoreInt * _Nullable)catalogPosition recipesCount:(int32_t)recipesCount settings:(MiamCorePackageSettings *)settings __attribute__((swift_name("doCopy(title:authorId:editable:shared:catalogCategory:catalogPosition:recipesCount:settings:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable authorId __attribute__((swift_name("authorId")));
@property (readonly) MiamCoreBoolean * _Nullable catalogCategory __attribute__((swift_name("catalogCategory")));
@property (readonly) MiamCoreInt * _Nullable catalogPosition __attribute__((swift_name("catalogPosition")));
@property (readonly) MiamCoreBoolean * _Nullable editable __attribute__((swift_name("editable")));
@property (readonly) int32_t recipesCount __attribute__((swift_name("recipesCount")));
@property (readonly) MiamCorePackageSettings *settings __attribute__((swift_name("settings")));
@property (readonly) MiamCoreBoolean * _Nullable shared __attribute__((swift_name("shared")));
@property (readonly) NSString *title __attribute__((swift_name("title")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PackageAttributes.Companion")))
@interface MiamCorePackageAttributesCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCorePackageAttributesCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PackageRelationships")))
@interface MiamCorePackageRelationships : MiamCoreRelationships
- (instancetype)initWithRecipes:(MiamCoreRecipeRelationshipList * _Nullable)recipes __attribute__((swift_name("init(recipes:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCorePackageRelationshipsCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
- (MiamCoreRecipeRelationshipList * _Nullable)component1 __attribute__((swift_name("component1()")));
- (MiamCorePackageRelationships *)doCopyRecipes:(MiamCoreRecipeRelationshipList * _Nullable)recipes __attribute__((swift_name("doCopy(recipes:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property MiamCoreRecipeRelationshipList * _Nullable recipes __attribute__((swift_name("recipes")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PackageRelationships.Companion")))
@interface MiamCorePackageRelationshipsCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCorePackageRelationshipsCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PackageSettings")))
@interface MiamCorePackageSettings : MiamCoreBase
- (instancetype)initWithSubtitle:(NSString * _Nullable)subtitle __attribute__((swift_name("init(subtitle:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCorePackageSettingsCompanion *companion __attribute__((swift_name("companion")));
- (NSString * _Nullable)component1 __attribute__((swift_name("component1()")));
- (MiamCorePackageSettings *)doCopySubtitle:(NSString * _Nullable)subtitle __attribute__((swift_name("doCopy(subtitle:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property NSString * _Nullable subtitle __attribute__((swift_name("subtitle")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PackageSettings.Companion")))
@interface MiamCorePackageSettingsCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCorePackageSettingsCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PointOfSale")))
@interface MiamCorePointOfSale : MiamCoreBase
- (instancetype)initWithId:(int32_t)id attributes:(MiamCorePointOfSaleAttributes *)attributes __attribute__((swift_name("init(id:attributes:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCorePointOfSaleCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (MiamCorePointOfSaleAttributes *)component2 __attribute__((swift_name("component2()")));
- (MiamCorePointOfSale *)doCopyId:(int32_t)id attributes:(MiamCorePointOfSaleAttributes *)attributes __attribute__((swift_name("doCopy(id:attributes:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCorePointOfSaleAttributes *attributes __attribute__((swift_name("attributes")));
@property (readonly) int32_t id __attribute__((swift_name("id")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PointOfSale.Companion")))
@interface MiamCorePointOfSaleCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCorePointOfSaleCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PointOfSaleAttributes")))
@interface MiamCorePointOfSaleAttributes : MiamCoreBase
- (instancetype)initWithName:(NSString * _Nullable)name settings:(MiamCorePointOfSaleSettings * _Nullable)settings activeItemsCount:(MiamCoreInt * _Nullable)activeItemsCount distance:(NSString * _Nullable)distance address:(NSString * _Nullable)address latitude:(NSString * _Nullable)latitude longitude:(NSString * _Nullable)longitude supplier:(MiamCoreSupplier * _Nullable)supplier __attribute__((swift_name("init(name:settings:activeItemsCount:distance:address:latitude:longitude:supplier:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCorePointOfSaleAttributesCompanion *companion __attribute__((swift_name("companion")));
- (NSString * _Nullable)component1 __attribute__((swift_name("component1()")));
- (MiamCorePointOfSaleSettings * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreInt * _Nullable)component3 __attribute__((swift_name("component3()")));
- (NSString * _Nullable)component4 __attribute__((swift_name("component4()")));
- (NSString * _Nullable)component5 __attribute__((swift_name("component5()")));
- (NSString * _Nullable)component6 __attribute__((swift_name("component6()")));
- (NSString * _Nullable)component7 __attribute__((swift_name("component7()")));
- (MiamCoreSupplier * _Nullable)component8 __attribute__((swift_name("component8()")));
- (MiamCorePointOfSaleAttributes *)doCopyName:(NSString * _Nullable)name settings:(MiamCorePointOfSaleSettings * _Nullable)settings activeItemsCount:(MiamCoreInt * _Nullable)activeItemsCount distance:(NSString * _Nullable)distance address:(NSString * _Nullable)address latitude:(NSString * _Nullable)latitude longitude:(NSString * _Nullable)longitude supplier:(MiamCoreSupplier * _Nullable)supplier __attribute__((swift_name("doCopy(name:settings:activeItemsCount:distance:address:latitude:longitude:supplier:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreInt * _Nullable activeItemsCount __attribute__((swift_name("activeItemsCount")));
@property (readonly) NSString * _Nullable address __attribute__((swift_name("address")));
@property (readonly) NSString * _Nullable distance __attribute__((swift_name("distance")));
@property (readonly) NSString * _Nullable latitude __attribute__((swift_name("latitude")));
@property (readonly) NSString * _Nullable longitude __attribute__((swift_name("longitude")));
@property (readonly) NSString * _Nullable name __attribute__((swift_name("name")));
@property (readonly) MiamCorePointOfSaleSettings * _Nullable settings __attribute__((swift_name("settings")));
@property MiamCoreSupplier * _Nullable supplier __attribute__((swift_name("supplier")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PointOfSaleAttributes.Companion")))
@interface MiamCorePointOfSaleAttributesCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCorePointOfSaleAttributesCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PointOfSaleOption")))
@interface MiamCorePointOfSaleOption : MiamCoreBase
- (instancetype)initWithEnabled:(MiamCoreBoolean * _Nullable)enabled logo:(NSString *)logo __attribute__((swift_name("init(enabled:logo:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCorePointOfSaleOptionCompanion *companion __attribute__((swift_name("companion")));
- (MiamCoreBoolean * _Nullable)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (MiamCorePointOfSaleOption *)doCopyEnabled:(MiamCoreBoolean * _Nullable)enabled logo:(NSString *)logo __attribute__((swift_name("doCopy(enabled:logo:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreBoolean * _Nullable enabled __attribute__((swift_name("enabled")));
@property (readonly) NSString *logo __attribute__((swift_name("logo")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PointOfSaleOption.Companion")))
@interface MiamCorePointOfSaleOptionCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCorePointOfSaleOptionCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PointOfSaleSettings")))
@interface MiamCorePointOfSaleSettings : MiamCoreBase
- (instancetype)initWithDrive:(MiamCorePointOfSaleOption * _Nullable)drive delivery:(MiamCorePointOfSaleOption * _Nullable)delivery mobile_warning:(BOOL)mobile_warning __attribute__((swift_name("init(drive:delivery:mobile_warning:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCorePointOfSaleSettingsCompanion *companion __attribute__((swift_name("companion")));
- (MiamCorePointOfSaleOption * _Nullable)component1 __attribute__((swift_name("component1()")));
- (MiamCorePointOfSaleOption * _Nullable)component2 __attribute__((swift_name("component2()")));
- (BOOL)component3 __attribute__((swift_name("component3()")));
- (MiamCorePointOfSaleSettings *)doCopyDrive:(MiamCorePointOfSaleOption * _Nullable)drive delivery:(MiamCorePointOfSaleOption * _Nullable)delivery mobile_warning:(BOOL)mobile_warning __attribute__((swift_name("doCopy(drive:delivery:mobile_warning:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCorePointOfSaleOption * _Nullable delivery __attribute__((swift_name("delivery")));
@property (readonly) MiamCorePointOfSaleOption * _Nullable drive __attribute__((swift_name("drive")));
@property (readonly) BOOL mobile_warning __attribute__((swift_name("mobile_warning")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PointOfSaleSettings.Companion")))
@interface MiamCorePointOfSaleSettingsCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCorePointOfSaleSettingsCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PointOfSaleWrapper")))
@interface MiamCorePointOfSaleWrapper : MiamCoreBase
- (instancetype)initWithData:(MiamCorePointOfSale *)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCorePointOfSaleWrapperCompanion *companion __attribute__((swift_name("companion")));
- (MiamCorePointOfSale *)component1 __attribute__((swift_name("component1()")));
- (MiamCorePointOfSaleWrapper *)doCopyData:(MiamCorePointOfSale *)data __attribute__((swift_name("doCopy(data:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCorePointOfSale *data __attribute__((swift_name("data")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PointOfSaleWrapper.Companion")))
@interface MiamCorePointOfSaleWrapperCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCorePointOfSaleWrapperCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PointOfSales")))
@interface MiamCorePointOfSales : MiamCoreBase
- (instancetype)initWithData:(NSArray<MiamCorePointOfSale *> *)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCorePointOfSalesCompanion *companion __attribute__((swift_name("companion")));
- (NSArray<MiamCorePointOfSale *> *)component1 __attribute__((swift_name("component1()")));
- (MiamCorePointOfSales *)doCopyData:(NSArray<MiamCorePointOfSale *> *)data __attribute__((swift_name("doCopy(data:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<MiamCorePointOfSale *> *data __attribute__((swift_name("data")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PointOfSales.Companion")))
@interface MiamCorePointOfSalesCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCorePointOfSalesCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Pricing")))
@interface MiamCorePricing : MiamCoreBase
- (instancetype)initWithPrice:(double)price serves:(int32_t)serves __attribute__((swift_name("init(price:serves:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCorePricingCompanion *companion __attribute__((swift_name("companion")));
- (double)component1 __attribute__((swift_name("component1()")));
- (int32_t)component2 __attribute__((swift_name("component2()")));
- (MiamCorePricing *)doCopyPrice:(double)price serves:(int32_t)serves __attribute__((swift_name("doCopy(price:serves:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) double price __attribute__((swift_name("price")));
@property (readonly) double pricePerServe __attribute__((swift_name("pricePerServe")));
@property (readonly) int32_t serves __attribute__((swift_name("serves")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Pricing.Companion")))
@interface MiamCorePricingCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCorePricingCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Recipe")))
@interface MiamCoreRecipe : MiamCoreRecord <MiamCoreBasketPreviewEntry>
- (instancetype)initWithId:(NSString *)id attributes:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)attributes json_relationships:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)json_relationships includedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("init(id:attributes:json_relationships:includedRecords:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreRecipeCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreRecipeAttributes * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreRecipeRelationships * _Nullable)component3 __attribute__((swift_name("component3()")));
- (MiamCoreRecipeLike * _Nullable)component4 __attribute__((swift_name("component4()")));
- (MiamCoreRecipe *)doCopyId:(NSString *)id attributes:(MiamCoreRecipeAttributes * _Nullable)attributes relationships:(MiamCoreRecipeRelationships * _Nullable)relationships recipeLike:(MiamCoreRecipeLike * _Nullable)recipeLike __attribute__((swift_name("doCopy(id:attributes:relationships:recipeLike:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreRecipeAttributes * _Nullable attributes __attribute__((swift_name("attributes")));
@property (readonly) NSString *cookingTimeIos __attribute__((swift_name("cookingTimeIos")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString *preparationTimeIos __attribute__((swift_name("preparationTimeIos")));
@property (readonly) MiamCoreRecipeLike * _Nullable recipeLike __attribute__((swift_name("recipeLike")));
@property (readonly) MiamCoreRecipeRelationships * _Nullable relationships __attribute__((swift_name("relationships")));
@property (readonly) NSString *restingTimeIos __attribute__((swift_name("restingTimeIos")));
@property (readonly) NSArray<MiamCoreRecipeStep *> *sortedStep __attribute__((swift_name("sortedStep")));
@property (readonly) NSString *totalTime __attribute__((swift_name("totalTime")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Recipe.Companion")))
@interface MiamCoreRecipeCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeAttributes")))
@interface MiamCoreRecipeAttributes : MiamCoreAttributes
- (instancetype)initWithTitle:(NSString *)title extId:(NSString * _Nullable)extId extLink:(NSString * _Nullable)extLink recipeDescription:(NSString * _Nullable)recipeDescription numberOfGuests:(int32_t)numberOfGuests ingredientsStr:(NSArray<NSString *> * _Nullable)ingredientsStr preparationTime:(id _Nullable)preparationTime preheatingTime:(id _Nullable)preheatingTime cookingTime:(id _Nullable)cookingTime restingTime:(id _Nullable)restingTime mediaUrl:(NSString *)mediaUrl source:(NSString * _Nullable)source informationalPageHtml:(NSString * _Nullable)informationalPageHtml filigraneLogoUrl:(NSString * _Nullable)filigraneLogoUrl informationalSentence:(NSString * _Nullable)informationalSentence difficulty:(MiamCoreInt * _Nullable)difficulty cost:(MiamCoreInt * _Nullable)cost suggested:(MiamCoreBoolean * _Nullable)suggested popularity:(MiamCoreInt * _Nullable)popularity videoId:(NSString * _Nullable)videoId promoted:(MiamCoreBoolean * _Nullable)promoted __attribute__((swift_name("init(title:extId:extLink:recipeDescription:numberOfGuests:ingredientsStr:preparationTime:preheatingTime:cookingTime:restingTime:mediaUrl:source:informationalPageHtml:filigraneLogoUrl:informationalSentence:difficulty:cost:suggested:popularity:videoId:promoted:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreRecipeAttributesCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (id _Nullable)component10 __attribute__((swift_name("component10()")));
- (NSString *)component11 __attribute__((swift_name("component11()")));
- (NSString * _Nullable)component12 __attribute__((swift_name("component12()")));
- (NSString * _Nullable)component13 __attribute__((swift_name("component13()")));
- (NSString * _Nullable)component14 __attribute__((swift_name("component14()")));
- (NSString * _Nullable)component15 __attribute__((swift_name("component15()")));
- (MiamCoreInt * _Nullable)component16 __attribute__((swift_name("component16()")));
- (MiamCoreInt * _Nullable)component17 __attribute__((swift_name("component17()")));
- (MiamCoreBoolean * _Nullable)component18 __attribute__((swift_name("component18()")));
- (MiamCoreInt * _Nullable)component19 __attribute__((swift_name("component19()")));
- (NSString * _Nullable)component2 __attribute__((swift_name("component2()")));
- (NSString * _Nullable)component20 __attribute__((swift_name("component20()")));
- (MiamCoreBoolean * _Nullable)component21 __attribute__((swift_name("component21()")));
- (NSString * _Nullable)component3 __attribute__((swift_name("component3()")));
- (NSString * _Nullable)component4 __attribute__((swift_name("component4()")));
- (int32_t)component5 __attribute__((swift_name("component5()")));
- (NSArray<NSString *> * _Nullable)component6 __attribute__((swift_name("component6()")));
- (id _Nullable)component7 __attribute__((swift_name("component7()")));
- (id _Nullable)component8 __attribute__((swift_name("component8()")));
- (id _Nullable)component9 __attribute__((swift_name("component9()")));
- (MiamCoreRecipeAttributes *)doCopyTitle:(NSString *)title extId:(NSString * _Nullable)extId extLink:(NSString * _Nullable)extLink recipeDescription:(NSString * _Nullable)recipeDescription numberOfGuests:(int32_t)numberOfGuests ingredientsStr:(NSArray<NSString *> * _Nullable)ingredientsStr preparationTime:(id _Nullable)preparationTime preheatingTime:(id _Nullable)preheatingTime cookingTime:(id _Nullable)cookingTime restingTime:(id _Nullable)restingTime mediaUrl:(NSString *)mediaUrl source:(NSString * _Nullable)source informationalPageHtml:(NSString * _Nullable)informationalPageHtml filigraneLogoUrl:(NSString * _Nullable)filigraneLogoUrl informationalSentence:(NSString * _Nullable)informationalSentence difficulty:(MiamCoreInt * _Nullable)difficulty cost:(MiamCoreInt * _Nullable)cost suggested:(MiamCoreBoolean * _Nullable)suggested popularity:(MiamCoreInt * _Nullable)popularity videoId:(NSString * _Nullable)videoId promoted:(MiamCoreBoolean * _Nullable)promoted __attribute__((swift_name("doCopy(title:extId:extLink:recipeDescription:numberOfGuests:ingredientsStr:preparationTime:preheatingTime:cookingTime:restingTime:mediaUrl:source:informationalPageHtml:filigraneLogoUrl:informationalSentence:difficulty:cost:suggested:popularity:videoId:promoted:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) id _Nullable cookingTime __attribute__((swift_name("cookingTime")));
@property (readonly) MiamCoreInt * _Nullable cost __attribute__((swift_name("cost")));
@property (readonly) MiamCoreInt * _Nullable difficulty __attribute__((swift_name("difficulty")));
@property (readonly) NSString * _Nullable extId __attribute__((swift_name("extId")));
@property (readonly) NSString * _Nullable extLink __attribute__((swift_name("extLink")));
@property (readonly) NSString * _Nullable filigraneLogoUrl __attribute__((swift_name("filigraneLogoUrl")));
@property (readonly) NSString * _Nullable informationalPageHtml __attribute__((swift_name("informationalPageHtml")));
@property (readonly) NSString * _Nullable informationalSentence __attribute__((swift_name("informationalSentence")));
@property (readonly) NSArray<NSString *> * _Nullable ingredientsStr __attribute__((swift_name("ingredientsStr")));
@property (readonly) NSString *mediaUrl __attribute__((swift_name("mediaUrl")));
@property (readonly) int32_t numberOfGuests __attribute__((swift_name("numberOfGuests")));
@property (readonly) MiamCoreInt * _Nullable popularity __attribute__((swift_name("popularity")));
@property (readonly) id _Nullable preheatingTime __attribute__((swift_name("preheatingTime")));
@property (readonly) id _Nullable preparationTime __attribute__((swift_name("preparationTime")));
@property (readonly) MiamCoreBoolean * _Nullable promoted __attribute__((swift_name("promoted")));
@property (readonly) NSString * _Nullable recipeDescription __attribute__((swift_name("recipeDescription")));
@property (readonly) id _Nullable restingTime __attribute__((swift_name("restingTime")));
@property (readonly) NSString * _Nullable source __attribute__((swift_name("source")));
@property (readonly) MiamCoreBoolean * _Nullable suggested __attribute__((swift_name("suggested")));
@property (readonly) NSString *title __attribute__((swift_name("title")));
@property (readonly) NSString * _Nullable videoId __attribute__((swift_name("videoId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeAttributes.Companion")))
@interface MiamCoreRecipeAttributesCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeAttributesCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeInfos")))
@interface MiamCoreRecipeInfos : MiamCoreBase
- (instancetype)initWithId:(int32_t)id guests:(int32_t)guests __attribute__((swift_name("init(id:guests:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCoreRecipeInfosCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (int32_t)component2 __attribute__((swift_name("component2()")));
- (MiamCoreRecipeInfos *)doCopyId:(int32_t)id guests:(int32_t)guests __attribute__((swift_name("doCopy(id:guests:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property int32_t guests __attribute__((swift_name("guests")));
@property (readonly) int32_t id __attribute__((swift_name("id")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeInfos.Companion")))
@interface MiamCoreRecipeInfosCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeInfosCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeLike")))
@interface MiamCoreRecipeLike : MiamCoreRecord <MiamCoreBasketPreviewEntry>
- (instancetype)initWithId:(NSString * _Nullable)id attributes:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)attributes json_relationships:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)json_relationships includedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("init(id:attributes:json_relationships:includedRecords:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreRecipeLikeCompanion *companion __attribute__((swift_name("companion")));
- (NSString * _Nullable)component1 __attribute__((swift_name("component1()")));
- (MiamCoreRecipeLikeAttributes * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreRecipeLikeRelationships * _Nullable)component3 __attribute__((swift_name("component3()")));
- (MiamCoreRecipeLike *)doCopyId:(NSString * _Nullable)id attributes:(MiamCoreRecipeLikeAttributes * _Nullable)attributes relationships:(MiamCoreRecipeLikeRelationships * _Nullable)relationships __attribute__((swift_name("doCopy(id:attributes:relationships:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
- (MiamCoreRecipeLike *)toggle __attribute__((swift_name("toggle()")));
@property (readonly) MiamCoreRecipeLikeAttributes * _Nullable attributes __attribute__((swift_name("attributes")));
@property (readonly) BOOL exists __attribute__((swift_name("exists")));
@property (readonly) NSString * _Nullable id __attribute__((swift_name("id")));
@property (readonly) BOOL isLiked __attribute__((swift_name("isLiked")));
@property (readonly) MiamCoreRecipeLikeRelationships * _Nullable relationships __attribute__((swift_name("relationships")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeLike.Companion")))
@interface MiamCoreRecipeLikeCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeLikeCompanion *shared __attribute__((swift_name("shared")));
- (MiamCoreRecipeLike *)createDefaultRecipeId:(NSString *)recipeId __attribute__((swift_name("createDefault(recipeId:)")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeLikeAttributes")))
@interface MiamCoreRecipeLikeAttributes : MiamCoreAttributes
- (instancetype)initWithIsPast:(BOOL)isPast recipeId:(int32_t)recipeId __attribute__((swift_name("init(isPast:recipeId:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreRecipeLikeAttributesCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)component1 __attribute__((swift_name("component1()")));
- (int32_t)component2 __attribute__((swift_name("component2()")));
- (MiamCoreRecipeLikeAttributes *)doCopyIsPast:(BOOL)isPast recipeId:(int32_t)recipeId __attribute__((swift_name("doCopy(isPast:recipeId:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL isPast __attribute__((swift_name("isPast")));
@property (readonly) int32_t recipeId __attribute__((swift_name("recipeId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeLikeAttributes.Companion")))
@interface MiamCoreRecipeLikeAttributesCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeLikeAttributesCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeLikeRelationships")))
@interface MiamCoreRecipeLikeRelationships : MiamCoreRelationships
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreRecipeLikeRelationshipsCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeLikeRelationships.Companion")))
@interface MiamCoreRecipeLikeRelationshipsCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeLikeRelationshipsCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeProvider")))
@interface MiamCoreRecipeProvider : MiamCoreRecord
- (instancetype)initWithId:(NSString *)id attributes:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)attributes json_relationships:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)json_relationships includedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("init(id:attributes:json_relationships:includedRecords:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreRecipeProviderCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreRecipeProviderAttributes * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreRecipeProviderRelationships * _Nullable)component3 __attribute__((swift_name("component3()")));
- (MiamCoreRecipeProvider *)doCopyId:(NSString *)id attributes:(MiamCoreRecipeProviderAttributes * _Nullable)attributes relationships:(MiamCoreRecipeProviderRelationships * _Nullable)relationships __attribute__((swift_name("doCopy(id:attributes:relationships:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreRecipeProviderAttributes * _Nullable attributes __attribute__((swift_name("attributes")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) MiamCoreRecipeProviderRelationships * _Nullable relationships __attribute__((swift_name("relationships")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeProvider.Companion")))
@interface MiamCoreRecipeProviderCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeProviderCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeProviderAttributes")))
@interface MiamCoreRecipeProviderAttributes : MiamCoreAttributes
- (instancetype)initWithName:(NSString *)name __attribute__((swift_name("init(name:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreRecipeProviderAttributesCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreRecipeProviderAttributes *)doCopyName:(NSString *)name __attribute__((swift_name("doCopy(name:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeProviderAttributes.Companion")))
@interface MiamCoreRecipeProviderAttributesCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeProviderAttributesCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeProviderRelationship")))
@interface MiamCoreRecipeProviderRelationship : MiamCoreRelationship
- (instancetype)initWithData:(MiamCoreRecipeProvider *)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreRecipeProviderRelationshipCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
@property MiamCoreRecipeProvider *data __attribute__((swift_name("data")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeProviderRelationship.Companion")))
@interface MiamCoreRecipeProviderRelationshipCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeProviderRelationshipCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeProviderRelationships")))
@interface MiamCoreRecipeProviderRelationships : MiamCoreRelationships
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreRecipeProviderRelationshipsCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeProviderRelationships.Companion")))
@interface MiamCoreRecipeProviderRelationshipsCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeProviderRelationshipsCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeProviderSerializer")))
@interface MiamCoreRecipeProviderSerializer : MiamCoreBase <MiamCoreKotlinx_serialization_coreKSerializer>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)recipeProviderSerializer __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeProviderSerializer *shared __attribute__((swift_name("shared")));
- (MiamCoreRecipeProviderRelationship *)deserializeDecoder:(id<MiamCoreKotlinx_serialization_coreDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (void)serializeEncoder:(id<MiamCoreKotlinx_serialization_coreEncoder>)encoder value:(MiamCoreRecipeProviderRelationship *)value __attribute__((swift_name("serialize(encoder:value:)")));
@property (readonly) id<MiamCoreKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeRelationshipList")))
@interface MiamCoreRecipeRelationshipList : MiamCoreRelationshipList
- (instancetype)initWithData:(NSArray<MiamCoreRecipe *> *)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreRecipeRelationshipListCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
@property NSArray<MiamCoreRecipe *> *data __attribute__((swift_name("data")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeRelationshipList.Companion")))
@interface MiamCoreRecipeRelationshipListCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeRelationshipListCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeRelationshipListSerializer")))
@interface MiamCoreRecipeRelationshipListSerializer : MiamCoreBase <MiamCoreKotlinx_serialization_coreKSerializer>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)recipeRelationshipListSerializer __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeRelationshipListSerializer *shared __attribute__((swift_name("shared")));
- (MiamCoreRecipeRelationshipList *)deserializeDecoder:(id<MiamCoreKotlinx_serialization_coreDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (void)serializeEncoder:(id<MiamCoreKotlinx_serialization_coreEncoder>)encoder value:(MiamCoreRecipeRelationshipList *)value __attribute__((swift_name("serialize(encoder:value:)")));
@property (readonly) id<MiamCoreKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeRelationships")))
@interface MiamCoreRecipeRelationships : MiamCoreRelationships
- (instancetype)initWithIngredients:(MiamCoreIngredientListRelationship * _Nullable)ingredients recipeProvider:(MiamCoreRecipeProviderRelationship * _Nullable)recipeProvider recipeStatus:(MiamCoreRecipeStatusRelationship * _Nullable)recipeStatus sponsors:(MiamCoreSponsorListRelationship * _Nullable)sponsors recipeSteps:(MiamCoreRecipeStepListRelationship * _Nullable)recipeSteps recipeType:(MiamCoreRecipeTypeRelationship * _Nullable)recipeType __attribute__((swift_name("init(ingredients:recipeProvider:recipeStatus:sponsors:recipeSteps:recipeType:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreRecipeRelationshipsCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
- (MiamCoreIngredientListRelationship * _Nullable)component1 __attribute__((swift_name("component1()")));
- (MiamCoreRecipeProviderRelationship * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreRecipeStatusRelationship * _Nullable)component3 __attribute__((swift_name("component3()")));
- (MiamCoreSponsorListRelationship * _Nullable)component4 __attribute__((swift_name("component4()")));
- (MiamCoreRecipeStepListRelationship * _Nullable)component5 __attribute__((swift_name("component5()")));
- (MiamCoreRecipeTypeRelationship * _Nullable)component6 __attribute__((swift_name("component6()")));
- (MiamCoreRecipeRelationships *)doCopyIngredients:(MiamCoreIngredientListRelationship * _Nullable)ingredients recipeProvider:(MiamCoreRecipeProviderRelationship * _Nullable)recipeProvider recipeStatus:(MiamCoreRecipeStatusRelationship * _Nullable)recipeStatus sponsors:(MiamCoreSponsorListRelationship * _Nullable)sponsors recipeSteps:(MiamCoreRecipeStepListRelationship * _Nullable)recipeSteps recipeType:(MiamCoreRecipeTypeRelationship * _Nullable)recipeType __attribute__((swift_name("doCopy(ingredients:recipeProvider:recipeStatus:sponsors:recipeSteps:recipeType:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property MiamCoreIngredientListRelationship * _Nullable ingredients __attribute__((swift_name("ingredients")));
@property MiamCoreRecipeProviderRelationship * _Nullable recipeProvider __attribute__((swift_name("recipeProvider")));
@property MiamCoreRecipeStatusRelationship * _Nullable recipeStatus __attribute__((swift_name("recipeStatus")));
@property MiamCoreRecipeStepListRelationship * _Nullable recipeSteps __attribute__((swift_name("recipeSteps")));
@property MiamCoreRecipeTypeRelationship * _Nullable recipeType __attribute__((swift_name("recipeType")));
@property MiamCoreSponsorListRelationship * _Nullable sponsors __attribute__((swift_name("sponsors")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeRelationships.Companion")))
@interface MiamCoreRecipeRelationshipsCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeRelationshipsCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeStatus")))
@interface MiamCoreRecipeStatus : MiamCoreRecord
- (instancetype)initWithId:(NSString *)id attributes:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)attributes json_relationships:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)json_relationships includedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("init(id:attributes:json_relationships:includedRecords:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreRecipeStatusCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreRecipeStatusAttributes * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreRecipeStatusRelationships * _Nullable)component3 __attribute__((swift_name("component3()")));
- (MiamCoreRecipeStatus *)doCopyId:(NSString *)id attributes:(MiamCoreRecipeStatusAttributes * _Nullable)attributes relationships:(MiamCoreRecipeStatusRelationships * _Nullable)relationships __attribute__((swift_name("doCopy(id:attributes:relationships:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreRecipeStatusAttributes * _Nullable attributes __attribute__((swift_name("attributes")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) MiamCoreRecipeStatusRelationships * _Nullable relationships __attribute__((swift_name("relationships")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeStatus.Companion")))
@interface MiamCoreRecipeStatusCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeStatusCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeStatusAttributes")))
@interface MiamCoreRecipeStatusAttributes : MiamCoreAttributes
- (instancetype)initWithName:(NSString *)name label:(NSString *)label __attribute__((swift_name("init(name:label:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreRecipeStatusAttributesCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (MiamCoreRecipeStatusAttributes *)doCopyName:(NSString *)name label:(NSString *)label __attribute__((swift_name("doCopy(name:label:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *label __attribute__((swift_name("label")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeStatusAttributes.Companion")))
@interface MiamCoreRecipeStatusAttributesCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeStatusAttributesCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeStatusRelationship")))
@interface MiamCoreRecipeStatusRelationship : MiamCoreRelationship
- (instancetype)initWithData:(MiamCoreRecipeStatus *)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreRecipeStatusRelationshipCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
@property MiamCoreRecipeStatus *data __attribute__((swift_name("data")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeStatusRelationship.Companion")))
@interface MiamCoreRecipeStatusRelationshipCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeStatusRelationshipCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeStatusRelationships")))
@interface MiamCoreRecipeStatusRelationships : MiamCoreRelationships
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreRecipeStatusRelationshipsCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeStatusRelationships.Companion")))
@interface MiamCoreRecipeStatusRelationshipsCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeStatusRelationshipsCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeStatusSerializer")))
@interface MiamCoreRecipeStatusSerializer : MiamCoreBase <MiamCoreKotlinx_serialization_coreKSerializer>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)recipeStatusSerializer __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeStatusSerializer *shared __attribute__((swift_name("shared")));
- (MiamCoreRecipeStatusRelationship *)deserializeDecoder:(id<MiamCoreKotlinx_serialization_coreDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (void)serializeEncoder:(id<MiamCoreKotlinx_serialization_coreEncoder>)encoder value:(MiamCoreRecipeStatusRelationship *)value __attribute__((swift_name("serialize(encoder:value:)")));
@property (readonly) id<MiamCoreKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeStep")))
@interface MiamCoreRecipeStep : MiamCoreRecord
- (instancetype)initWithId:(NSString *)id attributes:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)attributes json_relationships:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)json_relationships includedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("init(id:attributes:json_relationships:includedRecords:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithId:(NSString *)id attributes:(MiamCoreRecipeStepAttributes * _Nullable)attributes relationships:(MiamCoreRecipeStepRelationships * _Nullable)relationships __attribute__((swift_name("init(id:attributes:relationships:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreRecipeStepCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreRecipeStepAttributes * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreRecipeStepRelationships * _Nullable)component3 __attribute__((swift_name("component3()")));
- (MiamCoreRecipeStep *)doCopyId:(NSString *)id attributes:(MiamCoreRecipeStepAttributes * _Nullable)attributes relationships:(MiamCoreRecipeStepRelationships * _Nullable)relationships __attribute__((swift_name("doCopy(id:attributes:relationships:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreRecipeStepAttributes * _Nullable attributes __attribute__((swift_name("attributes")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) MiamCoreRecipeStepRelationships * _Nullable relationships __attribute__((swift_name("relationships")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeStep.Companion")))
@interface MiamCoreRecipeStepCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeStepCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeStepAttributes")))
@interface MiamCoreRecipeStepAttributes : MiamCoreAttributes
- (instancetype)initWithStepNumber:(int32_t)stepNumber title:(NSString * _Nullable)title stepDescription:(NSString * _Nullable)stepDescription mediaUrl:(NSString * _Nullable)mediaUrl __attribute__((swift_name("init(stepNumber:title:stepDescription:mediaUrl:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreRecipeStepAttributesCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (NSString * _Nullable)component2 __attribute__((swift_name("component2()")));
- (NSString * _Nullable)component3 __attribute__((swift_name("component3()")));
- (NSString * _Nullable)component4 __attribute__((swift_name("component4()")));
- (MiamCoreRecipeStepAttributes *)doCopyStepNumber:(int32_t)stepNumber title:(NSString * _Nullable)title stepDescription:(NSString * _Nullable)stepDescription mediaUrl:(NSString * _Nullable)mediaUrl __attribute__((swift_name("doCopy(stepNumber:title:stepDescription:mediaUrl:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable mediaUrl __attribute__((swift_name("mediaUrl")));
@property (readonly) NSString * _Nullable stepDescription __attribute__((swift_name("stepDescription")));
@property (readonly) int32_t stepNumber __attribute__((swift_name("stepNumber")));
@property (readonly) NSString * _Nullable title __attribute__((swift_name("title")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeStepAttributes.Companion")))
@interface MiamCoreRecipeStepAttributesCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeStepAttributesCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeStepListListSerializer")))
@interface MiamCoreRecipeStepListListSerializer : MiamCoreBase <MiamCoreKotlinx_serialization_coreKSerializer>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)recipeStepListListSerializer __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeStepListListSerializer *shared __attribute__((swift_name("shared")));
- (MiamCoreRecipeStepListRelationship *)deserializeDecoder:(id<MiamCoreKotlinx_serialization_coreDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (void)serializeEncoder:(id<MiamCoreKotlinx_serialization_coreEncoder>)encoder value:(MiamCoreRecipeStepListRelationship *)value __attribute__((swift_name("serialize(encoder:value:)")));
@property (readonly) id<MiamCoreKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeStepListRelationship")))
@interface MiamCoreRecipeStepListRelationship : MiamCoreRelationshipList
- (instancetype)initWithData:(NSArray<MiamCoreRecipeStep *> *)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreRecipeStepListRelationshipCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
@property NSArray<MiamCoreRecipeStep *> *data __attribute__((swift_name("data")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeStepListRelationship.Companion")))
@interface MiamCoreRecipeStepListRelationshipCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeStepListRelationshipCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeStepRelationships")))
@interface MiamCoreRecipeStepRelationships : MiamCoreRelationships
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreRecipeStepRelationshipsCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeStepRelationships.Companion")))
@interface MiamCoreRecipeStepRelationshipsCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeStepRelationshipsCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeType")))
@interface MiamCoreRecipeType : MiamCoreRecord
- (instancetype)initWithId:(NSString *)id attributes:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)attributes json_relationships:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)json_relationships includedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("init(id:attributes:json_relationships:includedRecords:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreRecipeTypeCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreRecipeTypeAttributes * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreRecipeTypeRelationships * _Nullable)component3 __attribute__((swift_name("component3()")));
- (MiamCoreRecipeType *)doCopyId:(NSString *)id attributes:(MiamCoreRecipeTypeAttributes * _Nullable)attributes relationships:(MiamCoreRecipeTypeRelationships * _Nullable)relationships __attribute__((swift_name("doCopy(id:attributes:relationships:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreRecipeTypeAttributes * _Nullable attributes __attribute__((swift_name("attributes")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) MiamCoreRecipeTypeRelationships * _Nullable relationships __attribute__((swift_name("relationships")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeType.Companion")))
@interface MiamCoreRecipeTypeCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeTypeCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeTypeAttributes")))
@interface MiamCoreRecipeTypeAttributes : MiamCoreAttributes
- (instancetype)initWithName:(NSString *)name __attribute__((swift_name("init(name:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreRecipeTypeAttributesCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreRecipeTypeAttributes *)doCopyName:(NSString *)name __attribute__((swift_name("doCopy(name:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeTypeAttributes.Companion")))
@interface MiamCoreRecipeTypeAttributesCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeTypeAttributesCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeTypeRelationship")))
@interface MiamCoreRecipeTypeRelationship : MiamCoreRelationship
- (instancetype)initWithData:(MiamCoreRecipeType *)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreRecipeTypeRelationshipCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
@property MiamCoreRecipeType *data __attribute__((swift_name("data")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeTypeRelationship.Companion")))
@interface MiamCoreRecipeTypeRelationshipCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeTypeRelationshipCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeTypeRelationships")))
@interface MiamCoreRecipeTypeRelationships : MiamCoreRelationships
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreRecipeTypeRelationshipsCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeTypeRelationships.Companion")))
@interface MiamCoreRecipeTypeRelationshipsCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeTypeRelationshipsCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeTypeSerializer")))
@interface MiamCoreRecipeTypeSerializer : MiamCoreBase <MiamCoreKotlinx_serialization_coreKSerializer>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)recipeTypeSerializer __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeTypeSerializer *shared __attribute__((swift_name("shared")));
- (MiamCoreRecipeTypeRelationship *)deserializeDecoder:(id<MiamCoreKotlinx_serialization_coreDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (void)serializeEncoder:(id<MiamCoreKotlinx_serialization_coreEncoder>)encoder value:(MiamCoreRecipeTypeRelationship *)value __attribute__((swift_name("serialize(encoder:value:)")));
@property (readonly) id<MiamCoreKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Record.Companion")))
@interface MiamCoreRecordCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecordCompanion *shared __attribute__((swift_name("shared")));
- (MiamCoreRecord * _Nullable)fromStringStr:(NSString *)str __attribute__((swift_name("fromString(str:)")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializerTypeParamsSerializers:(MiamCoreKotlinArray<id<MiamCoreKotlinx_serialization_coreKSerializer>> *)typeParamsSerializers __attribute__((swift_name("serializer(typeParamsSerializers:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecordCounterWrapper")))
@interface MiamCoreRecordCounterWrapper : MiamCoreBase
- (instancetype)initWithLinks:(MiamCoreRecordLink *)links data:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)data __attribute__((swift_name("init(links:data:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCoreRecordCounterWrapperCompanion *companion __attribute__((swift_name("companion")));
- (MiamCoreRecordLink *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreRecordCounterWrapper *)doCopyLinks:(MiamCoreRecordLink *)links data:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)data __attribute__((swift_name("doCopy(links:data:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (int32_t)getCount __attribute__((swift_name("getCount()")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable data __attribute__((swift_name("data")));
@property MiamCoreRecordLink *links __attribute__((swift_name("links")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecordCounterWrapper.Companion")))
@interface MiamCoreRecordCounterWrapperCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecordCounterWrapperCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecordLink")))
@interface MiamCoreRecordLink : MiamCoreBase
- (instancetype)initWithFirst:(NSString *)first last:(NSString *)last __attribute__((swift_name("init(first:last:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCoreRecordLinkCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (MiamCoreRecordLink *)doCopyFirst:(NSString *)first last:(NSString *)last __attribute__((swift_name("doCopy(first:last:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *first __attribute__((swift_name("first")));
@property (readonly) NSString *last __attribute__((swift_name("last")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecordLink.Companion")))
@interface MiamCoreRecordLinkCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecordLinkCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecordWrapper")))
@interface MiamCoreRecordWrapper : MiamCoreBase
- (instancetype)initWithData:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)data included:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)included __attribute__((swift_name("init(data:included:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCoreRecordWrapperCompanion *companion __attribute__((swift_name("companion")));
- (MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)component1 __attribute__((swift_name("component1()")));
- (MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreRecordWrapper *)doCopyData:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)data included:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)included __attribute__((swift_name("doCopy(data:included:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (MiamCoreRecord *)toRecord __attribute__((swift_name("toRecord()")));
- (NSArray<MiamCoreRecord *> *)toRecords __attribute__((swift_name("toRecords()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable data __attribute__((swift_name("data")));
@property MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable included __attribute__((swift_name("included")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecordWrapper.Companion")))
@interface MiamCoreRecordWrapperCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecordWrapperCompanion *shared __attribute__((swift_name("shared")));
- (MiamCoreRecordWrapper *)fromRecordRecord:(MiamCoreRecord *)record __attribute__((swift_name("fromRecord(record:)")));
- (MiamCoreRecordWrapper *)fromRecordsRecords:(NSArray<MiamCoreRecord *> *)records __attribute__((swift_name("fromRecords(records:)")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Relationship.Companion")))
@interface MiamCoreRelationshipCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRelationshipCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializerTypeParamsSerializers:(MiamCoreKotlinArray<id<MiamCoreKotlinx_serialization_coreKSerializer>> *)typeParamsSerializers __attribute__((swift_name("serializer(typeParamsSerializers:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RelationshipList.Companion")))
@interface MiamCoreRelationshipListCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRelationshipListCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializerTypeParamsSerializers:(MiamCoreKotlinArray<id<MiamCoreKotlinx_serialization_coreKSerializer>> *)typeParamsSerializers __attribute__((swift_name("serializer(typeParamsSerializers:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Relationships.Companion")))
@interface MiamCoreRelationshipsCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRelationshipsCompanion *shared __attribute__((swift_name("shared")));
- (MiamCoreKotlinx_serialization_jsonJsonElement *)filterEmptyRelationshipsRelationships:(MiamCoreKotlinx_serialization_jsonJsonElement *)relationships __attribute__((swift_name("filterEmptyRelationships(relationships:)")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializerTypeParamsSerializers:(MiamCoreKotlinArray<id<MiamCoreKotlinx_serialization_coreKSerializer>> *)typeParamsSerializers __attribute__((swift_name("serializer(typeParamsSerializers:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RetailerProduct")))
@interface MiamCoreRetailerProduct : MiamCoreBase
- (instancetype)initWithRetailerId:(NSString *)retailerId quantity:(int32_t)quantity name:(NSString * _Nullable)name productIdentifier:(NSString * _Nullable)productIdentifier __attribute__((swift_name("init(retailerId:quantity:name:productIdentifier:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (int32_t)component2 __attribute__((swift_name("component2()")));
- (NSString * _Nullable)component3 __attribute__((swift_name("component3()")));
- (NSString * _Nullable)component4 __attribute__((swift_name("component4()")));
- (MiamCoreRetailerProduct *)doCopyRetailerId:(NSString *)retailerId quantity:(int32_t)quantity name:(NSString * _Nullable)name productIdentifier:(NSString * _Nullable)productIdentifier __attribute__((swift_name("doCopy(retailerId:quantity:name:productIdentifier:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable name __attribute__((swift_name("name")));
@property (readonly) NSString * _Nullable productIdentifier __attribute__((swift_name("productIdentifier")));
@property (readonly) int32_t quantity __attribute__((swift_name("quantity")));
@property (readonly) NSString *retailerId __attribute__((swift_name("retailerId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Sponsor")))
@interface MiamCoreSponsor : MiamCoreRecord
- (instancetype)initWithId:(NSString *)id attributes:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)attributes json_relationships:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)json_relationships includedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("init(id:attributes:json_relationships:includedRecords:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreSponsorCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreSponsorAttributes * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreSponsorRelationships * _Nullable)component3 __attribute__((swift_name("component3()")));
- (MiamCoreSponsor *)doCopyId:(NSString *)id attributes:(MiamCoreSponsorAttributes * _Nullable)attributes relationships:(MiamCoreSponsorRelationships * _Nullable)relationships __attribute__((swift_name("doCopy(id:attributes:relationships:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreSponsorAttributes * _Nullable attributes __attribute__((swift_name("attributes")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) MiamCoreSponsorRelationships * _Nullable relationships __attribute__((swift_name("relationships")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Sponsor.Companion")))
@interface MiamCoreSponsorCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreSponsorCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SponsorAttributes")))
@interface MiamCoreSponsorAttributes : MiamCoreAttributes
- (instancetype)initWithName:(NSString *)name logoUrl:(NSString *)logoUrl __attribute__((swift_name("init(name:logoUrl:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreSponsorAttributesCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (MiamCoreSponsorAttributes *)doCopyName:(NSString *)name logoUrl:(NSString *)logoUrl __attribute__((swift_name("doCopy(name:logoUrl:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *logoUrl __attribute__((swift_name("logoUrl")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SponsorAttributes.Companion")))
@interface MiamCoreSponsorAttributesCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreSponsorAttributesCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SponsorListRelationship")))
@interface MiamCoreSponsorListRelationship : MiamCoreRelationshipList
- (instancetype)initWithData:(NSArray<MiamCoreSponsor *> *)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreSponsorListRelationshipCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
@property NSArray<MiamCoreSponsor *> *data __attribute__((swift_name("data")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SponsorListRelationship.Companion")))
@interface MiamCoreSponsorListRelationshipCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreSponsorListRelationshipCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SponsorListSerializer")))
@interface MiamCoreSponsorListSerializer : MiamCoreBase <MiamCoreKotlinx_serialization_coreKSerializer>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)sponsorListSerializer __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreSponsorListSerializer *shared __attribute__((swift_name("shared")));
- (MiamCoreSponsorListRelationship *)deserializeDecoder:(id<MiamCoreKotlinx_serialization_coreDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (void)serializeEncoder:(id<MiamCoreKotlinx_serialization_coreEncoder>)encoder value:(MiamCoreSponsorListRelationship *)value __attribute__((swift_name("serialize(encoder:value:)")));
@property (readonly) id<MiamCoreKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SponsorRelationships")))
@interface MiamCoreSponsorRelationships : MiamCoreRelationships
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreSponsorRelationshipsCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SponsorRelationships.Companion")))
@interface MiamCoreSponsorRelationshipsCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreSponsorRelationshipsCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SuggestionsCriteria")))
@interface MiamCoreSuggestionsCriteria : MiamCoreBase
- (instancetype)initWithShelfIngredientsIds:(NSArray<NSString *> * _Nullable)shelfIngredientsIds currentIngredientsIds:(NSArray<NSString *> * _Nullable)currentIngredientsIds basketIngredientsIds:(NSArray<NSString *> * _Nullable)basketIngredientsIds groupId:(NSString * _Nullable)groupId __attribute__((swift_name("init(shelfIngredientsIds:currentIngredientsIds:basketIngredientsIds:groupId:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCoreSuggestionsCriteriaCompanion *companion __attribute__((swift_name("companion")));
- (NSArray<NSString *> * _Nullable)component1 __attribute__((swift_name("component1()")));
- (NSArray<NSString *> * _Nullable)component2 __attribute__((swift_name("component2()")));
- (NSArray<NSString *> * _Nullable)component3 __attribute__((swift_name("component3()")));
- (NSString * _Nullable)component4 __attribute__((swift_name("component4()")));
- (MiamCoreSuggestionsCriteria *)doCopyShelfIngredientsIds:(NSArray<NSString *> * _Nullable)shelfIngredientsIds currentIngredientsIds:(NSArray<NSString *> * _Nullable)currentIngredientsIds basketIngredientsIds:(NSArray<NSString *> * _Nullable)basketIngredientsIds groupId:(NSString * _Nullable)groupId __attribute__((swift_name("doCopy(shelfIngredientsIds:currentIngredientsIds:basketIngredientsIds:groupId:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<NSString *> * _Nullable basketIngredientsIds __attribute__((swift_name("basketIngredientsIds")));
@property (readonly) NSArray<NSString *> * _Nullable currentIngredientsIds __attribute__((swift_name("currentIngredientsIds")));
@property (readonly) NSString * _Nullable groupId __attribute__((swift_name("groupId")));
@property (readonly) NSArray<NSString *> * _Nullable shelfIngredientsIds __attribute__((swift_name("shelfIngredientsIds")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SuggestionsCriteria.Companion")))
@interface MiamCoreSuggestionsCriteriaCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreSuggestionsCriteriaCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Supplier")))
@interface MiamCoreSupplier : MiamCoreBase
- (instancetype)initWithId:(int32_t)id attributes:(MiamCoreSupplierAttributes *)attributes __attribute__((swift_name("init(id:attributes:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCoreSupplierCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (MiamCoreSupplierAttributes *)component2 __attribute__((swift_name("component2()")));
- (MiamCoreSupplier *)doCopyId:(int32_t)id attributes:(MiamCoreSupplierAttributes *)attributes __attribute__((swift_name("doCopy(id:attributes:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreSupplierAttributes *attributes __attribute__((swift_name("attributes")));
@property (readonly) int32_t id __attribute__((swift_name("id")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Supplier.Companion")))
@interface MiamCoreSupplierCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreSupplierCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SupplierAttributes")))
@interface MiamCoreSupplierAttributes : MiamCoreBase
- (instancetype)initWithName:(NSString * _Nullable)name description:(NSString * _Nullable)description logo:(NSString * _Nullable)logo __attribute__((swift_name("init(name:description:logo:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCoreSupplierAttributesCompanion *companion __attribute__((swift_name("companion")));
- (NSString * _Nullable)component1 __attribute__((swift_name("component1()")));
- (NSString * _Nullable)component2 __attribute__((swift_name("component2()")));
- (NSString * _Nullable)component3 __attribute__((swift_name("component3()")));
- (MiamCoreSupplierAttributes *)doCopyName:(NSString * _Nullable)name description:(NSString * _Nullable)description logo:(NSString * _Nullable)logo __attribute__((swift_name("doCopy(name:description:logo:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable description_ __attribute__((swift_name("description_")));
@property (readonly) NSString * _Nullable logo __attribute__((swift_name("logo")));
@property (readonly) NSString * _Nullable name __attribute__((swift_name("name")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SupplierAttributes.Companion")))
@interface MiamCoreSupplierAttributesCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreSupplierAttributesCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SupplierNotificationWrapper")))
@interface MiamCoreSupplierNotificationWrapper : MiamCoreBase
- (instancetype)initWithToken:(NSString *)token status:(NSString *)status price:(NSString * _Nullable)price __attribute__((swift_name("init(token:status:price:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCoreSupplierNotificationWrapperCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString * _Nullable)component3 __attribute__((swift_name("component3()")));
- (MiamCoreSupplierNotificationWrapper *)doCopyToken:(NSString *)token status:(NSString *)status price:(NSString * _Nullable)price __attribute__((swift_name("doCopy(token:status:price:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable price __attribute__((swift_name("price")));
@property (readonly) NSString *status __attribute__((swift_name("status")));
@property (readonly) NSString *token __attribute__((swift_name("token")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SupplierNotificationWrapper.Companion")))
@interface MiamCoreSupplierNotificationWrapperCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreSupplierNotificationWrapperCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SupplierWrapper")))
@interface MiamCoreSupplierWrapper : MiamCoreBase
- (instancetype)initWithData:(MiamCoreSupplier *)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCoreSupplierWrapperCompanion *companion __attribute__((swift_name("companion")));
- (MiamCoreSupplier *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreSupplierWrapper *)doCopyData:(MiamCoreSupplier *)data __attribute__((swift_name("doCopy(data:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreSupplier *data __attribute__((swift_name("data")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SupplierWrapper.Companion")))
@interface MiamCoreSupplierWrapperCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreSupplierWrapperCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Tag")))
@interface MiamCoreTag : MiamCoreRecord
- (instancetype)initWithId:(NSString *)id attributes:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)attributes json_relationships:(MiamCoreKotlinx_serialization_jsonJsonElement * _Nullable)json_relationships includedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("init(id:attributes:json_relationships:includedRecords:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithId:(NSString *)id attributes:(MiamCoreTagAttributes * _Nullable)attributes relationships:(MiamCoreTagRelationships * _Nullable)relationships __attribute__((swift_name("init(id:attributes:relationships:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreTagCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreTagAttributes * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreTagRelationships * _Nullable)component3 __attribute__((swift_name("component3()")));
- (MiamCoreTag *)doCopyId:(NSString *)id attributes:(MiamCoreTagAttributes * _Nullable)attributes relationships:(MiamCoreTagRelationships * _Nullable)relationships __attribute__((swift_name("doCopy(id:attributes:relationships:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreTagAttributes * _Nullable attributes __attribute__((swift_name("attributes")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) MiamCoreTagRelationships * _Nullable relationships __attribute__((swift_name("relationships")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Tag.Companion")))
@interface MiamCoreTagCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreTagCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TagAttributes")))
@interface MiamCoreTagAttributes : MiamCoreAttributes
- (instancetype)initWithTagTypeId:(NSString *)tagTypeId name:(NSString *)name iconUrl:(NSString * _Nullable)iconUrl pictureUrl:(NSString * _Nullable)pictureUrl __attribute__((swift_name("init(tagTypeId:name:iconUrl:pictureUrl:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreTagAttributesCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString * _Nullable)component3 __attribute__((swift_name("component3()")));
- (NSString * _Nullable)component4 __attribute__((swift_name("component4()")));
- (MiamCoreTagAttributes *)doCopyTagTypeId:(NSString *)tagTypeId name:(NSString *)name iconUrl:(NSString * _Nullable)iconUrl pictureUrl:(NSString * _Nullable)pictureUrl __attribute__((swift_name("doCopy(tagTypeId:name:iconUrl:pictureUrl:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable iconUrl __attribute__((swift_name("iconUrl")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) NSString * _Nullable pictureUrl __attribute__((swift_name("pictureUrl")));
@property (readonly) NSString *tagTypeId __attribute__((swift_name("tagTypeId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TagAttributes.Companion")))
@interface MiamCoreTagAttributesCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreTagAttributesCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TagRelationships")))
@interface MiamCoreTagRelationships : MiamCoreRelationships
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreTagRelationshipsCompanion *companion __attribute__((swift_name("companion")));
- (void)buildFromIncludedIncludedRecords:(NSArray<MiamCoreRecord *> *)includedRecords __attribute__((swift_name("buildFromIncluded(includedRecords:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TagRelationships.Companion")))
@interface MiamCoreTagRelationshipsCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreTagRelationshipsCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((swift_name("KotlinComparable")))
@protocol MiamCoreKotlinComparable
@required
- (int32_t)compareToOther:(id _Nullable)other __attribute__((swift_name("compareTo(other:)")));
@end;

__attribute__((swift_name("KotlinEnum")))
@interface MiamCoreKotlinEnum<E> : MiamCoreBase <MiamCoreKotlinComparable>
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCoreKotlinEnumCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)compareToOther:(E)other __attribute__((swift_name("compareTo(other:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) int32_t ordinal __attribute__((swift_name("ordinal")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TagTypes")))
@interface MiamCoreTagTypes : MiamCoreKotlinEnum<MiamCoreTagTypes *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) MiamCoreTagTypes *diet __attribute__((swift_name("diet")));
@property (class, readonly) MiamCoreTagTypes *ingredient __attribute__((swift_name("ingredient")));
@property (class, readonly) MiamCoreTagTypes *equipment __attribute__((swift_name("equipment")));
+ (MiamCoreKotlinArray<MiamCoreTagTypes *> *)values __attribute__((swift_name("values()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DurationSerializer")))
@interface MiamCoreDurationSerializer : MiamCoreBase <MiamCoreKotlinx_serialization_coreKSerializer>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)durationSerializer __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreDurationSerializer *shared __attribute__((swift_name("shared")));
- (id)deserializeDecoder:(id<MiamCoreKotlinx_serialization_coreDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (void)serializeEncoder:(id<MiamCoreKotlinx_serialization_coreEncoder>)encoder value:(id)value __attribute__((swift_name("serialize(encoder:value:)")));
@property (readonly) id<MiamCoreKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end;

__attribute__((swift_name("BasketEntryRepository")))
@protocol MiamCoreBasketEntryRepository
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateBasketEntryBasketEntry:(MiamCoreBasketEntry *)basketEntry completionHandler:(void (^)(MiamCoreBasketEntry * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateBasketEntry(basketEntry:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketEntryRepositoryImp")))
@interface MiamCoreBasketEntryRepositoryImp : MiamCoreBase <MiamCoreBasketEntryRepository>
- (instancetype)initWithBasketEntryDataSource:(MiamCoreMiamAPIDatasource *)basketEntryDataSource __attribute__((swift_name("init(basketEntryDataSource:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateBasketEntryBasketEntry:(MiamCoreBasketEntry *)basketEntry completionHandler:(void (^)(MiamCoreBasketEntry * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateBasketEntry(basketEntry:completionHandler:)")));
@end;

__attribute__((swift_name("BasketRepository")))
@protocol MiamCoreBasketRepository
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getFromListAndPosListId:(NSString *)listId posId:(int32_t)posId completionHandler:(void (^)(MiamCoreBasket * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("getFromListAndPos(listId:posId:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateBasketBasket:(MiamCoreBasket *)basket completionHandler:(void (^)(MiamCoreBasket * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateBasket(basket:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketRepositoryImp")))
@interface MiamCoreBasketRepositoryImp : MiamCoreBase <MiamCoreBasketRepository, MiamCoreKoin_coreKoinComponent>
- (instancetype)initWithBasketDataSource:(MiamCoreMiamAPIDatasource *)basketDataSource __attribute__((swift_name("init(basketDataSource:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getFromListAndPosListId:(NSString *)listId posId:(int32_t)posId completionHandler:(void (^)(MiamCoreBasket * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("getFromListAndPos(listId:posId:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateBasketBasket:(MiamCoreBasket *)basket completionHandler:(void (^)(MiamCoreBasket * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateBasket(basket:completionHandler:)")));
@end;

__attribute__((swift_name("GroceriesEntryRepository")))
@protocol MiamCoreGroceriesEntryRepository
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateGrocerieEntryGe:(MiamCoreGroceriesEntry *)ge completionHandler:(void (^)(MiamCoreGroceriesEntry * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateGrocerieEntry(ge:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesEntryRepositoryImp")))
@interface MiamCoreGroceriesEntryRepositoryImp : MiamCoreBase <MiamCoreGroceriesEntryRepository>
- (instancetype)initWithGrocerieEntryDataSource:(MiamCoreMiamAPIDatasource *)grocerieEntryDataSource __attribute__((swift_name("init(grocerieEntryDataSource:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateGrocerieEntryGe:(MiamCoreGroceriesEntry *)ge completionHandler:(void (^)(MiamCoreGroceriesEntry * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateGrocerieEntry(ge:completionHandler:)")));
@end;

__attribute__((swift_name("GroceriesListRepository")))
@protocol MiamCoreGroceriesListRepository
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getCurrentWithCompletionHandler:(void (^)(MiamCoreGroceriesList * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getCurrent(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)removeAllRecipesWithCompletionHandler:(void (^)(MiamCoreGroceriesList * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("removeAllRecipes(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)removeRecipeFromListWithCompletionHandler:(void (^)(MiamCoreGroceriesList * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("removeRecipeFromList(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)resetWithCompletionHandler:(void (^)(MiamCoreGroceriesList * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("reset(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateGroceriesListGl:(MiamCoreGroceriesList *)gl completionHandler:(void (^)(MiamCoreGroceriesList * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateGroceriesList(gl:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateRecipeGuestsWithCompletionHandler:(void (^)(MiamCoreGroceriesList * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateRecipeGuests(completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesListRepositoryImp")))
@interface MiamCoreGroceriesListRepositoryImp : MiamCoreBase <MiamCoreGroceriesListRepository>
- (instancetype)initWithGroceriesListDataSource:(MiamCoreMiamAPIDatasource *)groceriesListDataSource __attribute__((swift_name("init(groceriesListDataSource:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getCurrentWithCompletionHandler:(void (^)(MiamCoreGroceriesList * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getCurrent(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)removeAllRecipesWithCompletionHandler:(void (^)(MiamCoreGroceriesList * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("removeAllRecipes(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)removeRecipeFromListWithCompletionHandler:(void (^)(MiamCoreGroceriesList * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("removeRecipeFromList(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)resetWithCompletionHandler:(void (^)(MiamCoreGroceriesList * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("reset(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateGroceriesListGl:(MiamCoreGroceriesList *)gl completionHandler:(void (^)(MiamCoreGroceriesList * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateGroceriesList(gl:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateRecipeGuestsWithCompletionHandler:(void (^)(MiamCoreGroceriesList * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateRecipeGuests(completionHandler:)")));
@end;

__attribute__((swift_name("PackageRepository")))
@protocol MiamCorePackageRepository
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getActivePackageForRetailerRetailerId:(NSString *)retailerId included:(NSArray<NSString *> *)included completionHandler:(void (^)(NSArray<MiamCorePackage *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getActivePackageForRetailer(retailerId:included:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PackageRepositoryImp")))
@interface MiamCorePackageRepositoryImp : MiamCoreBase <MiamCorePackageRepository>
- (instancetype)initWithPackageDataSource:(MiamCoreMiamAPIDatasource *)packageDataSource __attribute__((swift_name("init(packageDataSource:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getActivePackageForRetailerRetailerId:(NSString *)retailerId included:(NSArray<NSString *> *)included completionHandler:(void (^)(NSArray<MiamCorePackage *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getActivePackageForRetailer(retailerId:included:completionHandler:)")));
@end;

__attribute__((swift_name("PointOfSaleRepository")))
@protocol MiamCorePointOfSaleRepository
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getPosFormExtIdExtId:(NSString *)extId supplierId:(int32_t)supplierId completionHandler:(void (^)(MiamCorePointOfSale * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("getPosFormExtId(extId:supplierId:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PointOfSaleRepositoryImp")))
@interface MiamCorePointOfSaleRepositoryImp : MiamCoreBase <MiamCorePointOfSaleRepository>
- (instancetype)initWithPointOfSaleDataSource:(MiamCoreMiamAPIDatasource *)pointOfSaleDataSource __attribute__((swift_name("init(pointOfSaleDataSource:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getPosFormExtIdExtId:(NSString *)extId supplierId:(int32_t)supplierId completionHandler:(void (^)(MiamCorePointOfSale * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("getPosFormExtId(extId:supplierId:completionHandler:)")));
@end;

__attribute__((swift_name("PricingRepository")))
@protocol MiamCorePricingRepository
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipePriceRecipeId:(NSString *)recipeId posId:(int32_t)posId serves:(MiamCoreInt * _Nullable)serves completionHandler:(void (^)(MiamCorePricing * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipePrice(recipeId:posId:serves:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PricingRepositoryImp")))
@interface MiamCorePricingRepositoryImp : MiamCoreBase <MiamCorePricingRepository>
- (instancetype)initWithPriceDataSource:(MiamCoreMiamAPIDatasource *)priceDataSource __attribute__((swift_name("init(priceDataSource:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipePriceRecipeId:(NSString *)recipeId posId:(int32_t)posId serves:(MiamCoreInt * _Nullable)serves completionHandler:(void (^)(MiamCorePricing * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipePrice(recipeId:posId:serves:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeLikeRepositoryImp")))
@interface MiamCoreRecipeLikeRepositoryImp : MiamCoreBase
- (instancetype)initWithRecipeDataSource:(MiamCoreMiamAPIDatasource *)recipeDataSource __attribute__((swift_name("init(recipeDataSource:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createRecipeLikeRecipeLike:(MiamCoreRecipeLike *)recipeLike completionHandler:(void (^)(MiamCoreRecipeLike * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createRecipeLike(recipeLike:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipeLikesRecipeIds:(NSArray<NSString *> *)recipeIds completionHandler:(void (^)(NSArray<MiamCoreRecipeLike *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipeLikes(recipeIds:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateRecipeLikeRecipeLike:(MiamCoreRecipeLike *)recipeLike completionHandler:(void (^)(MiamCoreRecipeLike * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateRecipeLike(recipeLike:completionHandler:)")));
@end;

__attribute__((swift_name("RecipeRepository")))
@protocol MiamCoreRecipeRepository
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipeByIdRecipeId:(NSString *)recipeId completionHandler:(void (^)(MiamCoreRecipe * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipeById(recipeId:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipeNumberOfResultFilter:(NSString *)filter completionHandler:(void (^)(MiamCoreInt * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipeNumberOfResult(filter:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipeSuggestionSupplierId:(int32_t)supplierId criteria:(MiamCoreSuggestionsCriteria *)criteria completionHandler:(void (^)(MiamCoreRecipe * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipeSuggestion(supplierId:criteria:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipeSuggestionsSupplierId:(int32_t)supplierId criteria:(MiamCoreSuggestionsCriteria *)criteria size:(int32_t)size completionHandler:(void (^)(NSArray<MiamCoreRecipe *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipeSuggestions(supplierId:criteria:size:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipesByIdsRecipeIds:(NSArray<NSString *> *)recipeIds completionHandler:(void (^)(NSArray<MiamCoreRecipe *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipesByIds(recipeIds:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeRepositoryImp")))
@interface MiamCoreRecipeRepositoryImp : MiamCoreBase <MiamCoreRecipeRepository>
- (instancetype)initWithRecipeDataSource:(MiamCoreMiamAPIDatasource *)recipeDataSource __attribute__((swift_name("init(recipeDataSource:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCoreRecipeRepositoryImpCompanion *companion __attribute__((swift_name("companion")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipeByIdRecipeId:(NSString *)recipeId completionHandler:(void (^)(MiamCoreRecipe * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipeById(recipeId:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipeNumberOfResultFilter:(NSString *)filter completionHandler:(void (^)(MiamCoreInt * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipeNumberOfResult(filter:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipeSuggestionSupplierId:(int32_t)supplierId criteria:(MiamCoreSuggestionsCriteria *)criteria completionHandler:(void (^)(MiamCoreRecipe * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipeSuggestion(supplierId:criteria:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipeSuggestionsSupplierId:(int32_t)supplierId criteria:(MiamCoreSuggestionsCriteria *)criteria size:(int32_t)size completionHandler:(void (^)(NSArray<MiamCoreRecipe *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipeSuggestions(supplierId:criteria:size:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipesFilters:(NSDictionary<NSString *, NSString *> *)filters included:(NSArray<NSString *> *)included pageSize:(int32_t)pageSize pageNumber:(int32_t)pageNumber completionHandler:(void (^)(NSArray<MiamCoreRecipe *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipes(filters:included:pageSize:pageNumber:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipesByIdsRecipeIds:(NSArray<NSString *> *)recipeIds completionHandler:(void (^)(NSArray<MiamCoreRecipe *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipesByIds(recipeIds:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipesFromStringFilterFilters:(NSString *)filters included:(NSArray<NSString *> *)included pageSize:(int32_t)pageSize pageNumber:(int32_t)pageNumber completionHandler:(void (^)(NSArray<MiamCoreRecipe *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipesFromStringFilter(filters:included:pageSize:pageNumber:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeRepositoryImp.Companion")))
@interface MiamCoreRecipeRepositoryImpCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeRepositoryImpCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) NSArray<NSString *> *DEFAULT_INCLUDED __attribute__((swift_name("DEFAULT_INCLUDED")));
@property (readonly) int32_t DEFAULT_PAGESIZE __attribute__((swift_name("DEFAULT_PAGESIZE")));
@property (readonly) int32_t FIRST_PAGE __attribute__((swift_name("FIRST_PAGE")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SupplierRepositoryImp")))
@interface MiamCoreSupplierRepositoryImp : MiamCoreBase <MiamCoreKoin_coreKoinComponent>
- (instancetype)initWithRecipeDataSource:(MiamCoreMiamAPIDatasource *)recipeDataSource __attribute__((swift_name("init(recipeDataSource:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)notifyConfirmBasketBasketToken:(NSString *)basketToken completionHandler:(void (^)(MiamCoreKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("notifyConfirmBasket(basketToken:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)notifyPaidBasketBasketToken:(NSString *)basketToken price:(NSString *)price completionHandler:(void (^)(MiamCoreKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("notifyPaidBasket(basketToken:price:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TagsRepositoryImp")))
@interface MiamCoreTagsRepositoryImp : MiamCoreBase <MiamCoreKoin_coreKoinComponent>
- (instancetype)initWithTagDataSource:(MiamCoreMiamAPIDatasource *)tagDataSource __attribute__((swift_name("init(tagDataSource:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCoreTagsRepositoryImpCompanion *companion __attribute__((swift_name("companion")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)autocompleteSearchStr:(NSString *)searchStr completionHandler:(void (^)(NSArray<MiamCoreTag *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("autocomplete(searchStr:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)fetchDietTagsWithCompletionHandler:(void (^)(NSArray<MiamCoreTag *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("fetchDietTags(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)fetchEquipmentTagsWithCompletionHandler:(void (^)(NSArray<MiamCoreTag *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("fetchEquipmentTags(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getTagByIdId:(NSString *)id completionHandler:(void (^)(MiamCoreTag * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getTagById(id:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TagsRepositoryImp.Companion")))
@interface MiamCoreTagsRepositoryImpCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreTagsRepositoryImpCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) NSString *TAGTYPE __attribute__((swift_name("TAGTYPE")));
@end;

__attribute__((swift_name("BasketDataSource")))
@protocol MiamCoreBasketDataSource
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getFromListAndPosListId:(NSString *)listId posId:(int32_t)posId included:(NSArray<NSString *> *)included completionHandler:(void (^)(MiamCoreBasket * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("getFromListAndPos(listId:posId:included:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateBasketBasket:(MiamCoreBasket *)basket completionHandler:(void (^)(MiamCoreBasket * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateBasket(basket:completionHandler:)")));
@end;

__attribute__((swift_name("BasketEntryDataSource")))
@protocol MiamCoreBasketEntryDataSource
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateBasketEntryBasketEntry:(MiamCoreBasketEntry *)basketEntry included:(NSArray<NSString *> *)included completionHandler:(void (^)(MiamCoreBasketEntry * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateBasketEntry(basketEntry:included:completionHandler:)")));
@end;

__attribute__((swift_name("GrocerieEntryDataSource")))
@protocol MiamCoreGrocerieEntryDataSource
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateGroceriesEntryGe:(MiamCoreGroceriesEntry *)ge completionHandler:(void (^)(MiamCoreGroceriesEntry * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateGroceriesEntry(ge:completionHandler:)")));
@end;

__attribute__((swift_name("GroceriesListDataSource")))
@protocol MiamCoreGroceriesListDataSource
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getCurrentIncluded:(NSArray<NSString *> *)included completionHandler:(void (^)(MiamCoreGroceriesList * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getCurrent(included:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)resetWithCompletionHandler:(void (^)(MiamCoreGroceriesList * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("reset(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateGroceriesListGroceriesList:(MiamCoreGroceriesList *)groceriesList included:(NSArray<NSString *> *)included completionHandler:(void (^)(MiamCoreGroceriesList * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateGroceriesList(groceriesList:included:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("HttpRoutes")))
@interface MiamCoreHttpRoutes : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)httpRoutes __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreHttpRoutes *shared __attribute__((swift_name("shared")));
@property (readonly) NSString *BASKET_ENDPOINT __attribute__((swift_name("BASKET_ENDPOINT")));
@property (readonly) NSString *BASKET_ENTRIES_ENDPOINT __attribute__((swift_name("BASKET_ENTRIES_ENDPOINT")));
@property (readonly) NSString *GROCERIESLIST_ENDPOINT __attribute__((swift_name("GROCERIESLIST_ENDPOINT")));
@property (readonly) NSString *GROCERIES_ENTRY_ENDPOINT __attribute__((swift_name("GROCERIES_ENTRY_ENDPOINT")));
@property (readonly) NSString *PACKAGE_ENDPOINT __attribute__((swift_name("PACKAGE_ENDPOINT")));
@property (readonly) NSString *POINTOFSALE_ENDPOINT __attribute__((swift_name("POINTOFSALE_ENDPOINT")));
@property (readonly) NSString *RECIPE_ENDPOINT __attribute__((swift_name("RECIPE_ENDPOINT")));
@property (readonly) NSString *RECIPE_LIKE_ENDPOINT __attribute__((swift_name("RECIPE_LIKE_ENDPOINT")));
@property (readonly) NSString *RECIPE_SUGGESTIONS __attribute__((swift_name("RECIPE_SUGGESTIONS")));
@property (readonly) NSString *SUPPLIER __attribute__((swift_name("SUPPLIER")));
@property (readonly) NSString *TAGS_ENDPOINT __attribute__((swift_name("TAGS_ENDPOINT")));
@end;

__attribute__((swift_name("RecipeDataSource")))
@protocol MiamCoreRecipeDataSource
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createRecipeLikeRecipeLike:(MiamCoreRecipeLike *)recipeLike completionHandler:(void (^)(MiamCoreRecipeLike * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createRecipeLike(recipeLike:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipeByIdId:(NSString *)id included:(NSArray<NSString *> *)included completionHandler:(void (^)(MiamCoreRecipe * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipeById(id:included:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipeByIdsRecipesIds:(NSArray<NSString *> *)recipesIds included:(NSArray<NSString *> *)included pageSize:(int32_t)pageSize completionHandler:(void (^)(NSArray<MiamCoreRecipe *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipeByIds(recipesIds:included:pageSize:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipeLikesRecipesIds:(NSArray<NSString *> *)recipesIds pageSize:(int32_t)pageSize completionHandler:(void (^)(NSArray<MiamCoreRecipeLike *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipeLikes(recipesIds:pageSize:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipeNumberOfResultFilter:(NSString *)filter completionHandler:(void (^)(MiamCoreInt * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipeNumberOfResult(filter:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipeSuggestionsSupplierId:(int32_t)supplierId size:(MiamCoreInt * _Nullable)size criteria:(MiamCoreSuggestionsCriteria *)criteria included:(NSArray<NSString *> *)included completionHandler:(void (^)(NSArray<MiamCoreRecipe *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipeSuggestions(supplierId:size:criteria:included:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipesFilters:(NSDictionary<NSString *, NSString *> *)filters included:(NSArray<NSString *> *)included pageSize:(int32_t)pageSize pageNumber:(int32_t)pageNumber completionHandler:(void (^)(NSArray<MiamCoreRecipe *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipes(filters:included:pageSize:pageNumber:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipesFromStringFilterFilters:(NSString *)filters included:(NSArray<NSString *> *)included pageSize:(int32_t)pageSize pageNumber:(int32_t)pageNumber completionHandler:(void (^)(NSArray<MiamCoreRecipe *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipesFromStringFilter(filters:included:pageSize:pageNumber:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateRecipeLikeRecipeLike:(MiamCoreRecipeLike *)recipeLike completionHandler:(void (^)(MiamCoreRecipeLike * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateRecipeLike(recipeLike:completionHandler:)")));
@end;

__attribute__((swift_name("PointOfSaleDataSource")))
@protocol MiamCorePointOfSaleDataSource
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getPosFormExtIdExtId:(NSString *)extId supplierId:(int32_t)supplierId completionHandler:(void (^)(MiamCorePointOfSale * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("getPosFormExtId(extId:supplierId:completionHandler:)")));
@end;

__attribute__((swift_name("PricingDataSource")))
@protocol MiamCorePricingDataSource
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipePriceIdRecipe:(NSString *)idRecipe idPos:(int32_t)idPos serves:(MiamCoreInt * _Nullable)serves completionHandler:(void (^)(MiamCorePricing * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipePrice(idRecipe:idPos:serves:completionHandler:)")));
@end;

__attribute__((swift_name("SupplierDataSource")))
@protocol MiamCoreSupplierDataSource
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)notifyBasketUpdatedBasketToken:(NSString *)basketToken supplierId:(int32_t)supplierId status:(NSString *)status price:(NSString * _Nullable)price completionHandler:(void (^)(MiamCoreKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("notifyBasketUpdated(basketToken:supplierId:status:price:completionHandler:)")));
@end;

__attribute__((swift_name("PackageDataSource")))
@protocol MiamCorePackageDataSource
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getActivePackagesFromSupplierIDSupplierId:(NSString *)supplierId included:(NSArray<NSString *> *)included completionHandler:(void (^)(NSArray<MiamCorePackage *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getActivePackagesFromSupplierID(supplierId:included:completionHandler:)")));
@end;

__attribute__((swift_name("TagDataSource")))
@protocol MiamCoreTagDataSource
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)autocompleteTagSearchStr:(NSString *)searchStr completionHandler:(void (^)(NSArray<MiamCoreTag *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("autocompleteTag(searchStr:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getTagByIdId:(NSString *)id completionHandler:(void (^)(MiamCoreTag * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getTagById(id:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getTagsFilters:(NSDictionary<NSString *, NSString *> *)filters completionHandler:(void (^)(NSArray<MiamCoreTag *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getTags(filters:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MiamAPIDatasource")))
@interface MiamCoreMiamAPIDatasource : MiamCoreBase <MiamCoreRecipeDataSource, MiamCoreGroceriesListDataSource, MiamCorePointOfSaleDataSource, MiamCoreBasketDataSource, MiamCorePricingDataSource, MiamCoreBasketEntryDataSource, MiamCoreGrocerieEntryDataSource, MiamCoreSupplierDataSource, MiamCorePackageDataSource, MiamCoreTagDataSource, MiamCoreKoin_coreKoinComponent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)autocompleteTagSearchStr:(NSString *)searchStr completionHandler:(void (^)(NSArray<MiamCoreTag *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("autocompleteTag(searchStr:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createRecipeLikeRecipeLike:(MiamCoreRecipeLike *)recipeLike completionHandler:(void (^)(MiamCoreRecipeLike * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createRecipeLike(recipeLike:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getActivePackagesFromSupplierIDSupplierId:(NSString *)supplierId included:(NSArray<NSString *> *)included completionHandler:(void (^)(NSArray<MiamCorePackage *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getActivePackagesFromSupplierID(supplierId:included:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getCurrentIncluded:(NSArray<NSString *> *)included completionHandler:(void (^)(MiamCoreGroceriesList * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getCurrent(included:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getFromListAndPosListId:(NSString *)listId posId:(int32_t)posId included:(NSArray<NSString *> *)included completionHandler:(void (^)(MiamCoreBasket * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("getFromListAndPos(listId:posId:included:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getPosFormExtIdExtId:(NSString *)extId supplierId:(int32_t)supplierId completionHandler:(void (^)(MiamCorePointOfSale * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("getPosFormExtId(extId:supplierId:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipeByIdId:(NSString *)id included:(NSArray<NSString *> *)included completionHandler:(void (^)(MiamCoreRecipe * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipeById(id:included:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipeByIdsRecipesIds:(NSArray<NSString *> *)recipesIds included:(NSArray<NSString *> *)included pageSize:(int32_t)pageSize completionHandler:(void (^)(NSArray<MiamCoreRecipe *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipeByIds(recipesIds:included:pageSize:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipeLikesRecipesIds:(NSArray<NSString *> *)recipesIds pageSize:(int32_t)pageSize completionHandler:(void (^)(NSArray<MiamCoreRecipeLike *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipeLikes(recipesIds:pageSize:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipeNumberOfResultFilter:(NSString *)filter completionHandler:(void (^)(MiamCoreInt * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipeNumberOfResult(filter:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipePriceIdRecipe:(NSString *)idRecipe idPos:(int32_t)idPos serves:(MiamCoreInt * _Nullable)serves completionHandler:(void (^)(MiamCorePricing * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipePrice(idRecipe:idPos:serves:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipeSuggestionsSupplierId:(int32_t)supplierId size:(MiamCoreInt * _Nullable)size criteria:(MiamCoreSuggestionsCriteria *)criteria included:(NSArray<NSString *> *)included completionHandler:(void (^)(NSArray<MiamCoreRecipe *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipeSuggestions(supplierId:size:criteria:included:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipesFilters:(NSDictionary<NSString *, NSString *> *)filters included:(NSArray<NSString *> *)included pageSize:(int32_t)pageSize pageNumber:(int32_t)pageNumber completionHandler:(void (^)(NSArray<MiamCoreRecipe *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipes(filters:included:pageSize:pageNumber:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRecipesFromStringFilterFilters:(NSString *)filters included:(NSArray<NSString *> *)included pageSize:(int32_t)pageSize pageNumber:(int32_t)pageNumber completionHandler:(void (^)(NSArray<MiamCoreRecipe *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRecipesFromStringFilter(filters:included:pageSize:pageNumber:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getTagByIdId:(NSString *)id completionHandler:(void (^)(MiamCoreTag * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getTagById(id:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getTagsFilters:(NSDictionary<NSString *, NSString *> *)filters completionHandler:(void (^)(NSArray<MiamCoreTag *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getTags(filters:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)notifyBasketUpdatedBasketToken:(NSString *)basketToken supplierId:(int32_t)supplierId status:(NSString *)status price:(NSString * _Nullable)price completionHandler:(void (^)(MiamCoreKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("notifyBasketUpdated(basketToken:supplierId:status:price:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)resetWithCompletionHandler:(void (^)(MiamCoreGroceriesList * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("reset(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateBasketBasket:(MiamCoreBasket *)basket completionHandler:(void (^)(MiamCoreBasket * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateBasket(basket:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateBasketEntryBasketEntry:(MiamCoreBasketEntry *)basketEntry included:(NSArray<NSString *> *)included completionHandler:(void (^)(MiamCoreBasketEntry * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateBasketEntry(basketEntry:included:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateGroceriesEntryGe:(MiamCoreGroceriesEntry *)ge completionHandler:(void (^)(MiamCoreGroceriesEntry * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateGroceriesEntry(ge:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateGroceriesListGroceriesList:(MiamCoreGroceriesList *)groceriesList included:(NSArray<NSString *> *)included completionHandler:(void (^)(MiamCoreGroceriesList * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateGroceriesList(groceriesList:included:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateRecipeLikeRecipeLike:(MiamCoreRecipeLike *)recipeLike completionHandler:(void (^)(MiamCoreRecipeLike * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateRecipeLike(recipeLike:completionHandler:)")));
@end;

__attribute__((swift_name("IExecutorScope")))
@protocol MiamCoreIExecutorScope
@required
- (void)attach __attribute__((swift_name("attach()")));
- (void)detach __attribute__((swift_name("detach()")));
@end;

__attribute__((swift_name("MainIoExecutor")))
@interface MiamCoreMainIoExecutor : MiamCoreBase <MiamCoreIExecutorScope, MiamCoreKotlinx_coroutines_coreCoroutineScope, MiamCoreKoin_coreKoinComponent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)attach __attribute__((swift_name("attach()")));
- (void)collectFlow:(id<MiamCoreKotlinx_coroutines_coreFlow>)flow collect:(void (^)(id _Nullable))collect __attribute__((swift_name("collect(flow:collect:)")));
- (void)detach __attribute__((swift_name("detach()")));
- (void)launchFlow:(id<MiamCoreKotlinx_coroutines_coreFlow>)flow onSuccess:(void (^)(id _Nullable))onSuccess onError:(void (^ _Nullable)(MiamCoreKotlinThrowable *))onError __attribute__((swift_name("launch(flow:onSuccess:onError:)")));
@property (readonly) id<MiamCoreKotlinCoroutineContext> coroutineContext __attribute__((swift_name("coroutineContext")));
@property (readonly) MiamCoreKotlinx_coroutines_coreCoroutineDispatcher *ioDispatcher __attribute__((swift_name("ioDispatcher")));
@end;

__attribute__((swift_name("BaseViewModel")))
@interface MiamCoreBaseViewModel<Event, State, Effect> : MiamCoreMainIoExecutor
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (State)createInitialState __attribute__((swift_name("createInitialState()")));
- (void)handleEventEvent:(Event)event __attribute__((swift_name("handleEvent(event:)")));
- (void)setEffectBuilder:(Effect (^)(void))builder __attribute__((swift_name("setEffect(builder:)")));
- (void)setEventEvent:(Event)event __attribute__((swift_name("setEvent(event:)")));
- (void)setStateReduce:(State (^)(State))reduce __attribute__((swift_name("setState(reduce:)")));
@property (readonly) State currentState __attribute__((swift_name("currentState")));
@property (readonly) id<MiamCoreKotlinx_coroutines_coreFlow> effect __attribute__((swift_name("effect")));
@property (readonly) id<MiamCoreKotlinx_coroutines_coreSharedFlow> event __attribute__((swift_name("event")));
@property (readonly) id<MiamCoreKotlinx_coroutines_coreStateFlow> uiState __attribute__((swift_name("uiState")));
@end;

__attribute__((swift_name("LikeButtonViewModel")))
@interface MiamCoreLikeButtonViewModel : MiamCoreBaseViewModel<MiamCoreRecipeLikeContractEvent *, MiamCoreRecipeLikeContractState *, MiamCoreRecipeLikeContractEffect *>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (MiamCoreRecipeLikeContractState *)createInitialState __attribute__((swift_name("createInitialState()")));
- (void)handleEventEvent:(MiamCoreRecipeLikeContractEvent *)event __attribute__((swift_name("handleEvent(event:)")));
- (void)listenRecipeLikeChanges __attribute__((swift_name("listenRecipeLikeChanges()")));
- (void)setRecipeRecipeId:(NSString *)recipeId __attribute__((swift_name("setRecipe(recipeId:)")));
- (void)stopListenRecipeLikeChanges __attribute__((swift_name("stopListenRecipeLikeChanges()")));
- (void)toggleLike __attribute__((swift_name("toggleLike()")));
@end;

__attribute__((swift_name("RecipeLikeContract")))
@protocol MiamCoreRecipeLikeContract
@required
@end;

__attribute__((swift_name("UiEffect")))
@protocol MiamCoreUiEffect
@required
@end;

__attribute__((swift_name("RecipeLikeContractEffect")))
@interface MiamCoreRecipeLikeContractEffect : MiamCoreBase <MiamCoreUiEffect>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((swift_name("UiEvent")))
@protocol MiamCoreUiEvent
@required
@end;

__attribute__((swift_name("RecipeLikeContractEvent")))
@interface MiamCoreRecipeLikeContractEvent : MiamCoreBase <MiamCoreUiEvent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((swift_name("UiState")))
@protocol MiamCoreUiState
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeLikeContractState")))
@interface MiamCoreRecipeLikeContractState : MiamCoreBase <MiamCoreUiState>
- (instancetype)initWithRecipeId:(NSString * _Nullable)recipeId isLiked:(MiamCoreBasicUiState<MiamCoreBoolean *> *)isLiked __attribute__((swift_name("init(recipeId:isLiked:)"))) __attribute__((objc_designated_initializer));
- (NSString * _Nullable)component1 __attribute__((swift_name("component1()")));
- (MiamCoreBasicUiState<MiamCoreBoolean *> *)component2 __attribute__((swift_name("component2()")));
- (MiamCoreRecipeLikeContractState *)doCopyRecipeId:(NSString * _Nullable)recipeId isLiked:(MiamCoreBasicUiState<MiamCoreBoolean *> *)isLiked __attribute__((swift_name("doCopy(recipeId:isLiked:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreBasicUiState<MiamCoreBoolean *> *isLiked __attribute__((swift_name("isLiked")));
@property (readonly) NSString * _Nullable recipeId __attribute__((swift_name("recipeId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("QuantityFormatter")))
@interface MiamCoreQuantityFormatter : MiamCoreBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreQuantityFormatterDefault *companion __attribute__((swift_name("companion")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("QuantityFormatter.default")))
@interface MiamCoreQuantityFormatterDefault : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)default_ __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreQuantityFormatterDefault *shared __attribute__((swift_name("shared")));
- (NSString *)fracValue:(float)value maxdenominator:(int32_t)maxdenominator __attribute__((swift_name("frac(value:maxdenominator:)")));
- (NSString *)pluralizeUnit:(NSString *)unit __attribute__((swift_name("pluralize(unit:)")));
- (NSString *)readableFloatNumberValue:(NSString *)value unit:(NSString * _Nullable)unit __attribute__((swift_name("readableFloatNumber(value:unit:)")));
- (NSString *)realQuantitiesQuantity:(NSString *)quantity currentGuest:(int32_t)currentGuest recipeGuest:(int32_t)recipeGuest __attribute__((swift_name("realQuantities(quantity:currentGuest:recipeGuest:)")));
- (NSString *)render_fracOriginal_value:(float)original_value num:(float)num denom:(float)denom __attribute__((swift_name("render_frac(original_value:num:denom:)")));
- (NSString *)singularizeUnit:(NSString *)unit __attribute__((swift_name("singularize(unit:)")));
@end;

__attribute__((swift_name("BasketPreviewContract")))
@protocol MiamCoreBasketPreviewContract
@required
@end;

__attribute__((swift_name("BasketPreviewContractEffect")))
@interface MiamCoreBasketPreviewContractEffect : MiamCoreBase <MiamCoreUiEffect>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((swift_name("BasketPreviewContractEvent")))
@interface MiamCoreBasketPreviewContractEvent : MiamCoreBase <MiamCoreUiEvent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketPreviewContractEvent.AddEntry")))
@interface MiamCoreBasketPreviewContractEventAddEntry : MiamCoreBasketPreviewContractEvent
- (instancetype)initWithEntry:(MiamCoreBasketEntry *)entry __attribute__((swift_name("init(entry:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (MiamCoreBasketEntry *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreBasketPreviewContractEventAddEntry *)doCopyEntry:(MiamCoreBasketEntry *)entry __attribute__((swift_name("doCopy(entry:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreBasketEntry *entry __attribute__((swift_name("entry")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketPreviewContractEvent.CloseItemSelector")))
@interface MiamCoreBasketPreviewContractEventCloseItemSelector : MiamCoreBasketPreviewContractEvent
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)closeItemSelector __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreBasketPreviewContractEventCloseItemSelector *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketPreviewContractEvent.KillJob")))
@interface MiamCoreBasketPreviewContractEventKillJob : MiamCoreBasketPreviewContractEvent
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)killJob __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreBasketPreviewContractEventKillJob *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketPreviewContractEvent.OpenItemSelector")))
@interface MiamCoreBasketPreviewContractEventOpenItemSelector : MiamCoreBasketPreviewContractEvent
- (instancetype)initWithBpl:(MiamCoreBasketPreviewLine *)bpl __attribute__((swift_name("init(bpl:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (MiamCoreBasketPreviewLine *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreBasketPreviewContractEventOpenItemSelector *)doCopyBpl:(MiamCoreBasketPreviewLine *)bpl __attribute__((swift_name("doCopy(bpl:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreBasketPreviewLine *bpl __attribute__((swift_name("bpl")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketPreviewContractEvent.RemoveRecipe")))
@interface MiamCoreBasketPreviewContractEventRemoveRecipe : MiamCoreBasketPreviewContractEvent
- (instancetype)initWithRecipeId:(NSString *)recipeId __attribute__((swift_name("init(recipeId:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreBasketPreviewContractEventRemoveRecipe *)doCopyRecipeId:(NSString *)recipeId __attribute__((swift_name("doCopy(recipeId:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *recipeId __attribute__((swift_name("recipeId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketPreviewContractEvent.ReplaceItem")))
@interface MiamCoreBasketPreviewContractEventReplaceItem : MiamCoreBasketPreviewContractEvent
- (instancetype)initWithEntry:(MiamCoreBasketEntry *)entry itemId:(int32_t)itemId __attribute__((swift_name("init(entry:itemId:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (MiamCoreBasketEntry *)component1 __attribute__((swift_name("component1()")));
- (int32_t)component2 __attribute__((swift_name("component2()")));
- (MiamCoreBasketPreviewContractEventReplaceItem *)doCopyEntry:(MiamCoreBasketEntry *)entry itemId:(int32_t)itemId __attribute__((swift_name("doCopy(entry:itemId:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreBasketEntry *entry __attribute__((swift_name("entry")));
@property (readonly) int32_t itemId __attribute__((swift_name("itemId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketPreviewContractEvent.SetLines")))
@interface MiamCoreBasketPreviewContractEventSetLines : MiamCoreBasketPreviewContractEvent
- (instancetype)initWithNewlines:(MiamCoreBasketPreviewLine *)newlines __attribute__((swift_name("init(newlines:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (MiamCoreBasketPreviewLine *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreBasketPreviewContractEventSetLines *)doCopyNewlines:(MiamCoreBasketPreviewLine *)newlines __attribute__((swift_name("doCopy(newlines:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreBasketPreviewLine *newlines __attribute__((swift_name("newlines")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketPreviewContractEvent.SetRecipeId")))
@interface MiamCoreBasketPreviewContractEventSetRecipeId : MiamCoreBasketPreviewContractEvent
- (instancetype)initWithNewRecipeId:(int32_t)newRecipeId __attribute__((swift_name("init(newRecipeId:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (MiamCoreBasketPreviewContractEventSetRecipeId *)doCopyNewRecipeId:(int32_t)newRecipeId __attribute__((swift_name("doCopy(newRecipeId:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly, getter=doNewRecipeId) int32_t newRecipeId __attribute__((swift_name("newRecipeId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketPreviewContractEvent.ToggleLine")))
@interface MiamCoreBasketPreviewContractEventToggleLine : MiamCoreBasketPreviewContractEvent
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)toggleLine __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreBasketPreviewContractEventToggleLine *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketPreviewContractState")))
@interface MiamCoreBasketPreviewContractState : MiamCoreBase <MiamCoreUiState>
- (instancetype)initWithRecipeId:(MiamCoreInt * _Nullable)recipeId showLines:(BOOL)showLines line:(MiamCoreBasicUiState<MiamCoreBasketPreviewLine *> *)line bpl:(MiamCoreBasketPreviewLine * _Nullable)bpl isReloading:(BOOL)isReloading job:(id<MiamCoreKotlinx_coroutines_coreJob> _Nullable)job __attribute__((swift_name("init(recipeId:showLines:line:bpl:isReloading:job:)"))) __attribute__((objc_designated_initializer));
- (MiamCoreInt * _Nullable)component1 __attribute__((swift_name("component1()")));
- (BOOL)component2 __attribute__((swift_name("component2()")));
- (MiamCoreBasicUiState<MiamCoreBasketPreviewLine *> *)component3 __attribute__((swift_name("component3()")));
- (MiamCoreBasketPreviewLine * _Nullable)component4 __attribute__((swift_name("component4()")));
- (BOOL)component5 __attribute__((swift_name("component5()")));
- (id<MiamCoreKotlinx_coroutines_coreJob> _Nullable)component6 __attribute__((swift_name("component6()")));
- (MiamCoreBasketPreviewContractState *)doCopyRecipeId:(MiamCoreInt * _Nullable)recipeId showLines:(BOOL)showLines line:(MiamCoreBasicUiState<MiamCoreBasketPreviewLine *> *)line bpl:(MiamCoreBasketPreviewLine * _Nullable)bpl isReloading:(BOOL)isReloading job:(id<MiamCoreKotlinx_coroutines_coreJob> _Nullable)job __attribute__((swift_name("doCopy(recipeId:showLines:line:bpl:isReloading:job:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreBasketPreviewLine * _Nullable bpl __attribute__((swift_name("bpl")));
@property (readonly) BOOL isReloading __attribute__((swift_name("isReloading")));
@property (readonly) id<MiamCoreKotlinx_coroutines_coreJob> _Nullable job __attribute__((swift_name("job")));
@property (readonly) MiamCoreBasicUiState<MiamCoreBasketPreviewLine *> *line __attribute__((swift_name("line")));
@property (readonly) MiamCoreInt * _Nullable recipeId __attribute__((swift_name("recipeId")));
@property (readonly) BOOL showLines __attribute__((swift_name("showLines")));
@end;

__attribute__((swift_name("BasketPreviewViewModel")))
@interface MiamCoreBasketPreviewViewModel : MiamCoreBaseViewModel<MiamCoreBasketPreviewContractEvent *, MiamCoreBasketPreviewContractState *, MiamCoreBasketPreviewContractEffect *>
- (instancetype)initWithRecipeId:(NSString * _Nullable)recipeId __attribute__((swift_name("init(recipeId:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (MiamCoreBasketPreviewContractState *)createInitialState __attribute__((swift_name("createInitialState()")));
- (void)dispose __attribute__((swift_name("dispose()")));
- (void)handleEventEvent:(MiamCoreBasketPreviewContractEvent *)event __attribute__((swift_name("handleEvent(event:)")));
- (void)removeBasketEntryEntry:(MiamCoreBasketEntry *)entry __attribute__((swift_name("removeBasketEntry(entry:)")));
- (void)updateBasketEntryEntry:(MiamCoreBasketEntry *)entry finalQty:(int32_t)finalQty __attribute__((swift_name("updateBasketEntry(entry:finalQty:)")));
- (void)updateGuestUpdateGuest:(void (^)(MiamCoreInt *))updateGuest guestCount:(int32_t)guestCount __attribute__((swift_name("updateGuest(updateGuest:guestCount:)")));
@property (readonly) NSString * _Nullable recipeId __attribute__((swift_name("recipeId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketPreviewViewModel.LineUpdateState")))
@interface MiamCoreBasketPreviewViewModelLineUpdateState : MiamCoreBase <MiamCoreState>
- (instancetype)initWithLineUpdates:(NSArray<MiamCoreBasketEntry *> *)lineUpdates __attribute__((swift_name("init(lineUpdates:)"))) __attribute__((objc_designated_initializer));
- (NSArray<MiamCoreBasketEntry *> *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreBasketPreviewViewModelLineUpdateState *)doCopyLineUpdates:(NSArray<MiamCoreBasketEntry *> *)lineUpdates __attribute__((swift_name("doCopy(lineUpdates:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<MiamCoreBasketEntry *> *lineUpdates __attribute__((swift_name("lineUpdates")));
@end;

__attribute__((swift_name("ItemSelectorContract")))
@protocol MiamCoreItemSelectorContract
@required
@end;

__attribute__((swift_name("ItemSelectorContractEffect")))
@interface MiamCoreItemSelectorContractEffect : MiamCoreBase <MiamCoreUiEffect>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((swift_name("ItemSelectorContractEvent")))
@interface MiamCoreItemSelectorContractEvent : MiamCoreBase <MiamCoreUiEvent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ItemSelectorContractEvent.ReturnToBasketPreview")))
@interface MiamCoreItemSelectorContractEventReturnToBasketPreview : MiamCoreItemSelectorContractEvent
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)returnToBasketPreview __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreItemSelectorContractEventReturnToBasketPreview *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ItemSelectorContractEvent.SelectNewItem")))
@interface MiamCoreItemSelectorContractEventSelectNewItem : MiamCoreItemSelectorContractEvent
- (instancetype)initWithIndex:(int32_t)index __attribute__((swift_name("init(index:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (MiamCoreItemSelectorContractEventSelectNewItem *)doCopyIndex:(int32_t)index __attribute__((swift_name("doCopy(index:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t index __attribute__((swift_name("index")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ItemSelectorContractEvent.SetItemList")))
@interface MiamCoreItemSelectorContractEventSetItemList : MiamCoreItemSelectorContractEvent
- (instancetype)initWithItems:(NSArray<MiamCoreBasketPreviewLine *> *)items __attribute__((swift_name("init(items:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (NSArray<MiamCoreBasketPreviewLine *> *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreItemSelectorContractEventSetItemList *)doCopyItems:(NSArray<MiamCoreBasketPreviewLine *> *)items __attribute__((swift_name("doCopy(items:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<MiamCoreBasketPreviewLine *> *items __attribute__((swift_name("items")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ItemSelectorContractEvent.SetReplaceItemInPreview")))
@interface MiamCoreItemSelectorContractEventSetReplaceItemInPreview : MiamCoreItemSelectorContractEvent
- (instancetype)initWithReplace:(void (^)(MiamCoreBasketEntry *))replace __attribute__((swift_name("init(replace:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (void (^)(MiamCoreBasketEntry *))component1 __attribute__((swift_name("component1()")));
- (MiamCoreItemSelectorContractEventSetReplaceItemInPreview *)doCopyReplace:(void (^)(MiamCoreBasketEntry *))replace __attribute__((swift_name("doCopy(replace:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) void (^replace)(MiamCoreBasketEntry *) __attribute__((swift_name("replace")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ItemSelectorContractEvent.SetReturnToBasketPreview")))
@interface MiamCoreItemSelectorContractEventSetReturnToBasketPreview : MiamCoreItemSelectorContractEvent
- (instancetype)initWithReturnToPreview:(void (^)(void))returnToPreview __attribute__((swift_name("init(returnToPreview:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (void (^)(void))component1 __attribute__((swift_name("component1()")));
- (MiamCoreItemSelectorContractEventSetReturnToBasketPreview *)doCopyReturnToPreview:(void (^)(void))returnToPreview __attribute__((swift_name("doCopy(returnToPreview:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) void (^returnToPreview)(void) __attribute__((swift_name("returnToPreview")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ItemSelectorContractEvent.SetSelectedItem")))
@interface MiamCoreItemSelectorContractEventSetSelectedItem : MiamCoreItemSelectorContractEvent
- (instancetype)initWithItem:(MiamCoreBasketPreviewLine *)item __attribute__((swift_name("init(item:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (MiamCoreBasketPreviewLine *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreItemSelectorContractEventSetSelectedItem *)doCopyItem:(MiamCoreBasketPreviewLine *)item __attribute__((swift_name("doCopy(item:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreBasketPreviewLine *item __attribute__((swift_name("item")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ItemSelectorContractState")))
@interface MiamCoreItemSelectorContractState : MiamCoreBase <MiamCoreUiState>
- (instancetype)initWithSelectedItem:(MiamCoreBasketPreviewLine * _Nullable)selectedItem itemList:(NSArray<MiamCoreBasketPreviewLine *> * _Nullable)itemList replaceItemInPreview:(void (^)(MiamCoreBasketEntry *))replaceItemInPreview returnToPreview:(void (^)(void))returnToPreview __attribute__((swift_name("init(selectedItem:itemList:replaceItemInPreview:returnToPreview:)"))) __attribute__((objc_designated_initializer));
- (MiamCoreBasketPreviewLine * _Nullable)component1 __attribute__((swift_name("component1()")));
- (NSArray<MiamCoreBasketPreviewLine *> * _Nullable)component2 __attribute__((swift_name("component2()")));
- (void (^)(MiamCoreBasketEntry *))component3 __attribute__((swift_name("component3()")));
- (void (^)(void))component4 __attribute__((swift_name("component4()")));
- (MiamCoreItemSelectorContractState *)doCopySelectedItem:(MiamCoreBasketPreviewLine * _Nullable)selectedItem itemList:(NSArray<MiamCoreBasketPreviewLine *> * _Nullable)itemList replaceItemInPreview:(void (^)(MiamCoreBasketEntry *))replaceItemInPreview returnToPreview:(void (^)(void))returnToPreview __attribute__((swift_name("doCopy(selectedItem:itemList:replaceItemInPreview:returnToPreview:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<MiamCoreBasketPreviewLine *> * _Nullable itemList __attribute__((swift_name("itemList")));
@property (readonly) void (^replaceItemInPreview)(MiamCoreBasketEntry *) __attribute__((swift_name("replaceItemInPreview")));
@property (readonly) void (^returnToPreview)(void) __attribute__((swift_name("returnToPreview")));
@property (readonly) MiamCoreBasketPreviewLine * _Nullable selectedItem __attribute__((swift_name("selectedItem")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ItemSelectorInstance")))
@interface MiamCoreItemSelectorInstance : MiamCoreBase <MiamCoreKoin_coreKoinComponent>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)itemSelectorInstance __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreItemSelectorInstance *shared __attribute__((swift_name("shared")));
@property (readonly) MiamCoreItemSelectorViewModel *instance __attribute__((swift_name("instance")));
@end;

__attribute__((swift_name("ItemSelectorViewModel")))
@interface MiamCoreItemSelectorViewModel : MiamCoreBaseViewModel<MiamCoreItemSelectorContractEvent *, MiamCoreItemSelectorContractState *, MiamCoreItemSelectorContractEffect *> <MiamCoreKoin_coreKoinComponent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)chooseIndex:(int32_t)index __attribute__((swift_name("choose(index:)")));
- (MiamCoreItemSelectorContractState *)createInitialState __attribute__((swift_name("createInitialState()")));
- (void)handleEventEvent:(MiamCoreItemSelectorContractEvent *)event __attribute__((swift_name("handleEvent(event:)")));
@end;

__attribute__((swift_name("FavoritePageContract")))
@protocol MiamCoreFavoritePageContract
@required
@end;

__attribute__((swift_name("FavoritePageContractEffect")))
@interface MiamCoreFavoritePageContractEffect : MiamCoreBase <MiamCoreUiEffect>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((swift_name("FavoritePageContractEvent")))
@interface MiamCoreFavoritePageContractEvent : MiamCoreBase <MiamCoreUiEvent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FavoritePageContractEvent.LoadPage")))
@interface MiamCoreFavoritePageContractEventLoadPage : MiamCoreFavoritePageContractEvent
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)loadPage __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreFavoritePageContractEventLoadPage *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FavoritePageContractState")))
@interface MiamCoreFavoritePageContractState : MiamCoreBase <MiamCoreUiState>
- (instancetype)initWithFavoritesRecipes:(MiamCoreBasicUiState<NSArray<MiamCoreRecipe *> *> *)favoritesRecipes currentPage:(int32_t)currentPage isFetchingNewPage:(BOOL)isFetchingNewPage noMoreData:(BOOL)noMoreData __attribute__((swift_name("init(favoritesRecipes:currentPage:isFetchingNewPage:noMoreData:)"))) __attribute__((objc_designated_initializer));
- (MiamCoreBasicUiState<NSArray<MiamCoreRecipe *> *> *)component1 __attribute__((swift_name("component1()")));
- (int32_t)component2 __attribute__((swift_name("component2()")));
- (BOOL)component3 __attribute__((swift_name("component3()")));
- (BOOL)component4 __attribute__((swift_name("component4()")));
- (MiamCoreFavoritePageContractState *)doCopyFavoritesRecipes:(MiamCoreBasicUiState<NSArray<MiamCoreRecipe *> *> *)favoritesRecipes currentPage:(int32_t)currentPage isFetchingNewPage:(BOOL)isFetchingNewPage noMoreData:(BOOL)noMoreData __attribute__((swift_name("doCopy(favoritesRecipes:currentPage:isFetchingNewPage:noMoreData:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t currentPage __attribute__((swift_name("currentPage")));
@property (readonly) MiamCoreBasicUiState<NSArray<MiamCoreRecipe *> *> *favoritesRecipes __attribute__((swift_name("favoritesRecipes")));
@property (readonly) BOOL isFetchingNewPage __attribute__((swift_name("isFetchingNewPage")));
@property (readonly) BOOL noMoreData __attribute__((swift_name("noMoreData")));
@end;

__attribute__((swift_name("FavoritePageViewModel")))
@interface MiamCoreFavoritePageViewModel : MiamCoreBaseViewModel<MiamCoreFavoritePageContractEvent *, MiamCoreFavoritePageContractState *, MiamCoreFavoritePageContractEffect *>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreFavoritePageViewModelCompanion *companion __attribute__((swift_name("companion")));
- (MiamCoreFavoritePageContractState *)createInitialState __attribute__((swift_name("createInitialState()")));
- (void)handleEventEvent:(MiamCoreFavoritePageContractEvent *)event __attribute__((swift_name("handleEvent(event:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FavoritePageViewModel.Companion")))
@interface MiamCoreFavoritePageViewModelCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreFavoritePageViewModelCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) NSDictionary<NSString *, NSString *> *FILTERS __attribute__((swift_name("FILTERS")));
@end;

__attribute__((swift_name("MyMealContract")))
@protocol MiamCoreMyMealContract
@required
@end;

__attribute__((swift_name("MyMealContractEffect")))
@interface MiamCoreMyMealContractEffect : MiamCoreBase <MiamCoreUiEffect>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((swift_name("MyMealContractEvent")))
@interface MiamCoreMyMealContractEvent : MiamCoreBase <MiamCoreUiEvent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MyMealContractEvent.RemoveRecipe")))
@interface MiamCoreMyMealContractEventRemoveRecipe : MiamCoreMyMealContractEvent
- (instancetype)initWithRecipeId:(NSString *)recipeId __attribute__((swift_name("init(recipeId:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreMyMealContractEventRemoveRecipe *)doCopyRecipeId:(NSString *)recipeId __attribute__((swift_name("doCopy(recipeId:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *recipeId __attribute__((swift_name("recipeId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MyMealContractState")))
@interface MiamCoreMyMealContractState : MiamCoreBase <MiamCoreUiState>
- (instancetype)initWithLines:(MiamCoreBasicUiState<NSArray<MiamCoreBasketPreviewLine *> *> *)lines bpls:(NSArray<MiamCoreBasketPreviewLine *> * _Nullable)bpls job:(id<MiamCoreKotlinx_coroutines_coreJob> _Nullable)job __attribute__((swift_name("init(lines:bpls:job:)"))) __attribute__((objc_designated_initializer));
- (MiamCoreBasicUiState<NSArray<MiamCoreBasketPreviewLine *> *> *)component1 __attribute__((swift_name("component1()")));
- (NSArray<MiamCoreBasketPreviewLine *> * _Nullable)component2 __attribute__((swift_name("component2()")));
- (id<MiamCoreKotlinx_coroutines_coreJob> _Nullable)component3 __attribute__((swift_name("component3()")));
- (MiamCoreMyMealContractState *)doCopyLines:(MiamCoreBasicUiState<NSArray<MiamCoreBasketPreviewLine *> *> *)lines bpls:(NSArray<MiamCoreBasketPreviewLine *> * _Nullable)bpls job:(id<MiamCoreKotlinx_coroutines_coreJob> _Nullable)job __attribute__((swift_name("doCopy(lines:bpls:job:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<MiamCoreBasketPreviewLine *> * _Nullable bpls __attribute__((swift_name("bpls")));
@property (readonly) id<MiamCoreKotlinx_coroutines_coreJob> _Nullable job __attribute__((swift_name("job")));
@property (readonly) MiamCoreBasicUiState<NSArray<MiamCoreBasketPreviewLine *> *> *lines __attribute__((swift_name("lines")));
@end;

__attribute__((swift_name("MyMealViewModel")))
@interface MiamCoreMyMealViewModel : MiamCoreBaseViewModel<MiamCoreMyMealContractEvent *, MiamCoreMyMealContractState *, MiamCoreMyMealContractEffect *>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (MiamCoreMyMealContractState *)createInitialState __attribute__((swift_name("createInitialState()")));
- (void)handleEventEvent:(MiamCoreMyMealContractEvent *)event __attribute__((swift_name("handleEvent(event:)")));
@end;

__attribute__((swift_name("PricingContract")))
@protocol MiamCorePricingContract
@required
@end;

__attribute__((swift_name("PricingContractEffect")))
@interface MiamCorePricingContractEffect : MiamCoreBase <MiamCoreUiEffect>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((swift_name("PricingContractEvent")))
@interface MiamCorePricingContractEvent : MiamCoreBase <MiamCoreUiEvent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PricingContractState")))
@interface MiamCorePricingContractState : MiamCoreBase <MiamCoreUiState>
- (instancetype)initWithPrice:(MiamCoreBasicUiState<MiamCorePricing *> *)price recipeId:(NSString * _Nullable)recipeId guestNumber:(MiamCoreInt * _Nullable)guestNumber __attribute__((swift_name("init(price:recipeId:guestNumber:)"))) __attribute__((objc_designated_initializer));
- (MiamCoreBasicUiState<MiamCorePricing *> *)component1 __attribute__((swift_name("component1()")));
- (NSString * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreInt * _Nullable)component3 __attribute__((swift_name("component3()")));
- (MiamCorePricingContractState *)doCopyPrice:(MiamCoreBasicUiState<MiamCorePricing *> *)price recipeId:(NSString * _Nullable)recipeId guestNumber:(MiamCoreInt * _Nullable)guestNumber __attribute__((swift_name("doCopy(price:recipeId:guestNumber:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreInt * _Nullable guestNumber __attribute__((swift_name("guestNumber")));
@property (readonly) MiamCoreBasicUiState<MiamCorePricing *> *price __attribute__((swift_name("price")));
@property (readonly) NSString * _Nullable recipeId __attribute__((swift_name("recipeId")));
@end;

__attribute__((swift_name("RecipePricingViewModel")))
@interface MiamCoreRecipePricingViewModel : MiamCoreBaseViewModel<MiamCorePricingContractEvent *, MiamCorePricingContractState *, MiamCorePricingContractEffect *>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (MiamCorePricingContractState *)createInitialState __attribute__((swift_name("createInitialState()")));
- (void)handleEventEvent:(MiamCorePricingContractEvent *)event __attribute__((swift_name("handleEvent(event:)")));
- (void)listenBasketChanges __attribute__((swift_name("listenBasketChanges()")));
- (void)setRecipeRecipeId:(NSString *)recipeId guestNumber:(int32_t)guestNumber __attribute__((swift_name("setRecipe(recipeId:guestNumber:)")));
- (void)stopListenBasketChanges __attribute__((swift_name("stopListenBasketChanges()")));
@end;

__attribute__((swift_name("RecipeListPageContract")))
@protocol MiamCoreRecipeListPageContract
@required
@end;

__attribute__((swift_name("RecipeListPageContractEffect")))
@interface MiamCoreRecipeListPageContractEffect : MiamCoreBase <MiamCoreUiEffect>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((swift_name("RecipeListPageContractEvent")))
@interface MiamCoreRecipeListPageContractEvent : MiamCoreBase <MiamCoreUiEvent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeListPageContractEvent.InitPage")))
@interface MiamCoreRecipeListPageContractEventInitPage : MiamCoreRecipeListPageContractEvent
- (instancetype)initWithTitle:(NSString *)title __attribute__((swift_name("init(title:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreRecipeListPageContractEventInitPage *)doCopyTitle:(NSString *)title __attribute__((swift_name("doCopy(title:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *title __attribute__((swift_name("title")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeListPageContractEvent.LoadPage")))
@interface MiamCoreRecipeListPageContractEventLoadPage : MiamCoreRecipeListPageContractEvent
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)loadPage __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeListPageContractEventLoadPage *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeListPageContractState")))
@interface MiamCoreRecipeListPageContractState : MiamCoreBase <MiamCoreUiState>
- (instancetype)initWithRecipes:(MiamCoreBasicUiState<NSArray<MiamCoreRecipe *> *> *)recipes title:(NSString *)title filter:(NSString *)filter currentPage:(int32_t)currentPage isFetchingNewPage:(BOOL)isFetchingNewPage noMoreData:(BOOL)noMoreData __attribute__((swift_name("init(recipes:title:filter:currentPage:isFetchingNewPage:noMoreData:)"))) __attribute__((objc_designated_initializer));
- (MiamCoreBasicUiState<NSArray<MiamCoreRecipe *> *> *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (int32_t)component4 __attribute__((swift_name("component4()")));
- (BOOL)component5 __attribute__((swift_name("component5()")));
- (BOOL)component6 __attribute__((swift_name("component6()")));
- (MiamCoreRecipeListPageContractState *)doCopyRecipes:(MiamCoreBasicUiState<NSArray<MiamCoreRecipe *> *> *)recipes title:(NSString *)title filter:(NSString *)filter currentPage:(int32_t)currentPage isFetchingNewPage:(BOOL)isFetchingNewPage noMoreData:(BOOL)noMoreData __attribute__((swift_name("doCopy(recipes:title:filter:currentPage:isFetchingNewPage:noMoreData:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t currentPage __attribute__((swift_name("currentPage")));
@property (readonly) NSString *filter __attribute__((swift_name("filter")));
@property (readonly) BOOL isFetchingNewPage __attribute__((swift_name("isFetchingNewPage")));
@property (readonly) BOOL noMoreData __attribute__((swift_name("noMoreData")));
@property (readonly) MiamCoreBasicUiState<NSArray<MiamCoreRecipe *> *> *recipes __attribute__((swift_name("recipes")));
@property (readonly) NSString *title __attribute__((swift_name("title")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeListPageViewModel")))
@interface MiamCoreRecipeListPageViewModel : MiamCoreBaseViewModel<MiamCoreRecipeListPageContractEvent *, MiamCoreRecipeListPageContractState *, MiamCoreRecipeListPageContractEffect *>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (BOOL)canLoad __attribute__((swift_name("canLoad()")));
- (MiamCoreRecipeListPageContractState *)createInitialState __attribute__((swift_name("createInitialState()")));
- (void)handleEventEvent:(MiamCoreRecipeListPageContractEvent *)event __attribute__((swift_name("handleEvent(event:)")));
@end;

__attribute__((swift_name("MyMealButtonContract")))
@protocol MiamCoreMyMealButtonContract
@required
@end;

__attribute__((swift_name("MyMealButtonContractEffect")))
@interface MiamCoreMyMealButtonContractEffect : MiamCoreBase <MiamCoreUiEffect>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((swift_name("MyMealButtonContractEvent")))
@interface MiamCoreMyMealButtonContractEvent : MiamCoreBase <MiamCoreUiEvent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MyMealButtonContractState")))
@interface MiamCoreMyMealButtonContractState : MiamCoreBase <MiamCoreUiState>
- (instancetype)initWithRecipeCount:(MiamCoreBasicUiState<MiamCoreInt *> *)recipeCount __attribute__((swift_name("init(recipeCount:)"))) __attribute__((objc_designated_initializer));
- (MiamCoreBasicUiState<MiamCoreInt *> *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreMyMealButtonContractState *)doCopyRecipeCount:(MiamCoreBasicUiState<MiamCoreInt *> *)recipeCount __attribute__((swift_name("doCopy(recipeCount:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreBasicUiState<MiamCoreInt *> *recipeCount __attribute__((swift_name("recipeCount")));
@end;

__attribute__((swift_name("MyMealButtonViewModel")))
@interface MiamCoreMyMealButtonViewModel : MiamCoreBaseViewModel<MiamCoreMyMealButtonContractEvent *, MiamCoreMyMealButtonContractState *, MiamCoreMyMealButtonContractEffect *> <MiamCoreKoin_coreKoinComponent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (MiamCoreMyMealButtonContractState *)createInitialState __attribute__((swift_name("createInitialState()")));
- (void)handleEventEvent:(MiamCoreMyMealButtonContractEvent *)event __attribute__((swift_name("handleEvent(event:)")));
@end;

__attribute__((swift_name("PreferencesContract")))
@protocol MiamCorePreferencesContract
@required
@end;

__attribute__((swift_name("PreferencesContractEffect")))
@interface MiamCorePreferencesContractEffect : MiamCoreBase <MiamCoreUiEffect>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((swift_name("PreferencesContractEvent")))
@interface MiamCorePreferencesContractEvent : MiamCoreBase <MiamCoreUiEvent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PreferencesContractState")))
@interface MiamCorePreferencesContractState : MiamCoreBase <MiamCoreUiState>
- (instancetype)initWithBasicState:(MiamCoreBasicUiState<MiamCoreBoolean *> *)basicState diets:(NSArray<MiamCoreCheckableTag *> *)diets ingredients:(NSArray<MiamCoreCheckableTag *> *)ingredients equipments:(NSArray<MiamCoreCheckableTag *> *)equipments recipesFound:(int32_t)recipesFound guests:(int32_t)guests __attribute__((swift_name("init(basicState:diets:ingredients:equipments:recipesFound:guests:)"))) __attribute__((objc_designated_initializer));
- (MiamCoreBasicUiState<MiamCoreBoolean *> *)component1 __attribute__((swift_name("component1()")));
- (NSArray<MiamCoreCheckableTag *> *)component2 __attribute__((swift_name("component2()")));
- (NSArray<MiamCoreCheckableTag *> *)component3 __attribute__((swift_name("component3()")));
- (NSArray<MiamCoreCheckableTag *> *)component4 __attribute__((swift_name("component4()")));
- (int32_t)component5 __attribute__((swift_name("component5()")));
- (int32_t)component6 __attribute__((swift_name("component6()")));
- (MiamCorePreferencesContractState *)doCopyBasicState:(MiamCoreBasicUiState<MiamCoreBoolean *> *)basicState diets:(NSArray<MiamCoreCheckableTag *> *)diets ingredients:(NSArray<MiamCoreCheckableTag *> *)ingredients equipments:(NSArray<MiamCoreCheckableTag *> *)equipments recipesFound:(int32_t)recipesFound guests:(int32_t)guests __attribute__((swift_name("doCopy(basicState:diets:ingredients:equipments:recipesFound:guests:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreBasicUiState<MiamCoreBoolean *> *basicState __attribute__((swift_name("basicState")));
@property (readonly) NSArray<MiamCoreCheckableTag *> *diets __attribute__((swift_name("diets")));
@property (readonly) NSArray<MiamCoreCheckableTag *> *equipments __attribute__((swift_name("equipments")));
@property (readonly) int32_t guests __attribute__((swift_name("guests")));
@property (readonly) NSArray<MiamCoreCheckableTag *> *ingredients __attribute__((swift_name("ingredients")));
@property (readonly) int32_t recipesFound __attribute__((swift_name("recipesFound")));
@end;

__attribute__((swift_name("PreferencesEffect")))
@interface MiamCorePreferencesEffect : MiamCoreBase <MiamCoreEffect>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PreferencesEffect.PreferencesChanged")))
@interface MiamCorePreferencesEffectPreferencesChanged : MiamCorePreferencesEffect
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)preferencesChanged __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCorePreferencesEffectPreferencesChanged *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PreferencesEffect.PreferencesLoaded")))
@interface MiamCorePreferencesEffectPreferencesLoaded : MiamCorePreferencesEffect
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)preferencesLoaded __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCorePreferencesEffectPreferencesLoaded *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PreferencesViewModelInstance")))
@interface MiamCorePreferencesViewModelInstance : MiamCoreBase <MiamCoreKoin_coreKoinComponent>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)preferencesViewModelInstance __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCorePreferencesViewModelInstance *shared __attribute__((swift_name("shared")));
@property (readonly) MiamCoreSingletonPreferencesViewModel *instance __attribute__((swift_name("instance")));
@end;

__attribute__((swift_name("SingletonPreferencesViewModel")))
@interface MiamCoreSingletonPreferencesViewModel : MiamCoreBaseViewModel<MiamCorePreferencesContractEvent *, MiamCorePreferencesContractState *, MiamCorePreferencesContractEffect *>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreSingletonPreferencesViewModelCompanion *companion __attribute__((swift_name("companion")));
- (void)addIngredientPreferenceTag:(MiamCoreTag *)tag __attribute__((swift_name("addIngredientPreference(tag:)")));
- (void)applyPreferences __attribute__((swift_name("applyPreferences()")));
- (void)changeGlobaleGuestNumberOfGuest:(int32_t)numberOfGuest __attribute__((swift_name("changeGlobaleGuest(numberOfGuest:)")));
- (MiamCorePreferencesContractState *)createInitialState __attribute__((swift_name("createInitialState()")));
- (NSString *)getPreferencesAsQueryString __attribute__((swift_name("getPreferencesAsQueryString()")));
- (void)handleEventEvent:(MiamCorePreferencesContractEvent *)event __attribute__((swift_name("handleEvent(event:)")));
- (id<MiamCoreKotlinx_coroutines_coreFlow>)observeSideEffect __attribute__((swift_name("observeSideEffect()")));
- (void)resetPreferences __attribute__((swift_name("resetPreferences()")));
- (void)togglePreferenceTagIdToToggle:(NSString *)tagIdToToggle __attribute__((swift_name("togglePreference(tagIdToToggle:)")));
@property (readonly) NSArray<MiamCoreCheckableTag *> *allTags __attribute__((swift_name("allTags")));
@property (readonly) BOOL isInit __attribute__((swift_name("isInit")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SingletonPreferencesViewModel.Companion")))
@interface MiamCoreSingletonPreferencesViewModelCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreSingletonPreferencesViewModelCompanion *shared __attribute__((swift_name("shared")));
- (MiamCorePreferencesContractState *)getInitialPref __attribute__((swift_name("getInitialPref()")));
@property (readonly) NSString *LOCAL_DIET_KEY __attribute__((swift_name("LOCAL_DIET_KEY")));
@property (readonly) NSString *LOCAL_EQUIPMENT_KEY __attribute__((swift_name("LOCAL_EQUIPMENT_KEY")));
@property (readonly) NSString *LOCAL_INGREDIENT_KEY __attribute__((swift_name("LOCAL_INGREDIENT_KEY")));
@property (readonly) NSArray<NSString *> *defaultIngredientTagIds __attribute__((swift_name("defaultIngredientTagIds")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RouterContent")))
@interface MiamCoreRouterContent : MiamCoreKotlinEnum<MiamCoreRouterContent *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) MiamCoreRouterContent *recipeDetail __attribute__((swift_name("recipeDetail")));
@property (class, readonly) MiamCoreRouterContent *recipeHelper __attribute__((swift_name("recipeHelper")));
@property (class, readonly) MiamCoreRouterContent *recipeSponsor __attribute__((swift_name("recipeSponsor")));
@property (class, readonly) MiamCoreRouterContent *basketPreview __attribute__((swift_name("basketPreview")));
@property (class, readonly) MiamCoreRouterContent *itemsSelector __attribute__((swift_name("itemsSelector")));
@property (class, readonly) MiamCoreRouterContent *empty __attribute__((swift_name("empty")));
+ (MiamCoreKotlinArray<MiamCoreRouterContent *> *)values __attribute__((swift_name("values()")));
@end;

__attribute__((swift_name("RouterOutletContract")))
@protocol MiamCoreRouterOutletContract
@required
@end;

__attribute__((swift_name("RouterOutletContractEffect")))
@interface MiamCoreRouterOutletContractEffect : MiamCoreBase <MiamCoreUiEffect>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((swift_name("RouterOutletContractEvent")))
@interface MiamCoreRouterOutletContractEvent : MiamCoreBase <MiamCoreUiEvent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RouterOutletContractEvent.GoToDetail")))
@interface MiamCoreRouterOutletContractEventGoToDetail : MiamCoreRouterOutletContractEvent
- (instancetype)initWithVm:(MiamCoreRecipeViewModel *)vm withFooter:(BOOL)withFooter __attribute__((swift_name("init(vm:withFooter:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (MiamCoreRecipeViewModel *)component1 __attribute__((swift_name("component1()")));
- (BOOL)component2 __attribute__((swift_name("component2()")));
- (MiamCoreRouterOutletContractEventGoToDetail *)doCopyVm:(MiamCoreRecipeViewModel *)vm withFooter:(BOOL)withFooter __attribute__((swift_name("doCopy(vm:withFooter:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreRecipeViewModel *vm __attribute__((swift_name("vm")));
@property (readonly) BOOL withFooter __attribute__((swift_name("withFooter")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RouterOutletContractEvent.GoToHelper")))
@interface MiamCoreRouterOutletContractEventGoToHelper : MiamCoreRouterOutletContractEvent
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)goToHelper __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRouterOutletContractEventGoToHelper *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RouterOutletContractEvent.GoToItemSelector")))
@interface MiamCoreRouterOutletContractEventGoToItemSelector : MiamCoreRouterOutletContractEvent
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)goToItemSelector __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRouterOutletContractEventGoToItemSelector *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RouterOutletContractEvent.GoToPreview")))
@interface MiamCoreRouterOutletContractEventGoToPreview : MiamCoreRouterOutletContractEvent
- (instancetype)initWithRecipeId:(NSString *)recipeId vm:(MiamCoreRecipeViewModel *)vm __attribute__((swift_name("init(recipeId:vm:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreRecipeViewModel *)component2 __attribute__((swift_name("component2()")));
- (MiamCoreRouterOutletContractEventGoToPreview *)doCopyRecipeId:(NSString *)recipeId vm:(MiamCoreRecipeViewModel *)vm __attribute__((swift_name("doCopy(recipeId:vm:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *recipeId __attribute__((swift_name("recipeId")));
@property (readonly) MiamCoreRecipeViewModel *vm __attribute__((swift_name("vm")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RouterOutletContractEvent.GoToSponsor")))
@interface MiamCoreRouterOutletContractEventGoToSponsor : MiamCoreRouterOutletContractEvent
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)goToSponsor __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRouterOutletContractEventGoToSponsor *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RouterOutletContractEvent.OpenDialog")))
@interface MiamCoreRouterOutletContractEventOpenDialog : MiamCoreRouterOutletContractEvent
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)openDialog __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRouterOutletContractEventOpenDialog *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RouterOutletContractEvent.Previous")))
@interface MiamCoreRouterOutletContractEventPrevious : MiamCoreRouterOutletContractEvent
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)previous __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRouterOutletContractEventPrevious *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RouterOutletContractState")))
@interface MiamCoreRouterOutletContractState : MiamCoreBase <MiamCoreUiState>
- (instancetype)initWithContent:(MiamCoreRouterContent *)content rvm:(MiamCoreRecipeViewModel * _Nullable)rvm recipeId:(NSString * _Nullable)recipeId isOpen:(BOOL)isOpen showFooter:(BOOL)showFooter __attribute__((swift_name("init(content:rvm:recipeId:isOpen:showFooter:)"))) __attribute__((objc_designated_initializer));
- (MiamCoreRouterContent *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreRecipeViewModel * _Nullable)component2 __attribute__((swift_name("component2()")));
- (NSString * _Nullable)component3 __attribute__((swift_name("component3()")));
- (BOOL)component4 __attribute__((swift_name("component4()")));
- (BOOL)component5 __attribute__((swift_name("component5()")));
- (MiamCoreRouterOutletContractState *)doCopyContent:(MiamCoreRouterContent *)content rvm:(MiamCoreRecipeViewModel * _Nullable)rvm recipeId:(NSString * _Nullable)recipeId isOpen:(BOOL)isOpen showFooter:(BOOL)showFooter __attribute__((swift_name("doCopy(content:rvm:recipeId:isOpen:showFooter:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreRouterContent *content __attribute__((swift_name("content")));
@property (readonly) BOOL isOpen __attribute__((swift_name("isOpen")));
@property (readonly) NSString * _Nullable recipeId __attribute__((swift_name("recipeId")));
@property (readonly) MiamCoreRecipeViewModel * _Nullable rvm __attribute__((swift_name("rvm")));
@property (readonly) BOOL showFooter __attribute__((swift_name("showFooter")));
@end;

__attribute__((swift_name("RouterOutletViewModel")))
@interface MiamCoreRouterOutletViewModel : MiamCoreBaseViewModel<MiamCoreRouterOutletContractEvent *, MiamCoreRouterOutletContractState *, MiamCoreRouterOutletContractEffect *>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (MiamCoreRouterOutletContractState *)createInitialState __attribute__((swift_name("createInitialState()")));
- (void)goToDetailVmRecipe:(MiamCoreRecipeViewModel *)vmRecipe showDetailsFooter:(BOOL)showDetailsFooter __attribute__((swift_name("goToDetail(vmRecipe:showDetailsFooter:)")));
- (void)handleEventEvent:(MiamCoreRouterOutletContractEvent *)event __attribute__((swift_name("handleEvent(event:)")));
@end;

__attribute__((swift_name("PreferencesSearchContract")))
@protocol MiamCorePreferencesSearchContract
@required
@end;

__attribute__((swift_name("PreferencesSearchContractEffect")))
@interface MiamCorePreferencesSearchContractEffect : MiamCoreBase <MiamCoreUiEffect>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((swift_name("PreferencesSearchContractEvent")))
@interface MiamCorePreferencesSearchContractEvent : MiamCoreBase <MiamCoreUiEvent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PreferencesSearchContractState")))
@interface MiamCorePreferencesSearchContractState : MiamCoreBase <MiamCoreUiState>
- (instancetype)initWithSearchProposal:(MiamCoreBasicUiState<NSArray<MiamCoreTag *> *> *)searchProposal __attribute__((swift_name("init(searchProposal:)"))) __attribute__((objc_designated_initializer));
- (MiamCoreBasicUiState<NSArray<MiamCoreTag *> *> *)component1 __attribute__((swift_name("component1()")));
- (MiamCorePreferencesSearchContractState *)doCopySearchProposal:(MiamCoreBasicUiState<NSArray<MiamCoreTag *> *> *)searchProposal __attribute__((swift_name("doCopy(searchProposal:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreBasicUiState<NSArray<MiamCoreTag *> *> *searchProposal __attribute__((swift_name("searchProposal")));
@end;

__attribute__((swift_name("PreferencesSearchViewModel")))
@interface MiamCorePreferencesSearchViewModel : MiamCoreBaseViewModel<MiamCorePreferencesSearchContractEvent *, MiamCorePreferencesSearchContractState *, MiamCorePreferencesSearchContractEffect *>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (MiamCorePreferencesSearchContractState *)createInitialState __attribute__((swift_name("createInitialState()")));
- (void)handleEventEvent:(MiamCorePreferencesSearchContractEvent *)event __attribute__((swift_name("handleEvent(event:)")));
- (void)resetState __attribute__((swift_name("resetState()")));
- (void)searchSearch:(NSString *)search __attribute__((swift_name("search(search:)")));
@end;

__attribute__((swift_name("RecipeCarouselContract")))
@protocol MiamCoreRecipeCarouselContract
@required
@end;

__attribute__((swift_name("RecipeCarouselContractEffect")))
@interface MiamCoreRecipeCarouselContractEffect : MiamCoreBase <MiamCoreUiEffect>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((swift_name("RecipeCarouselContractEvent")))
@interface MiamCoreRecipeCarouselContractEvent : MiamCoreBase <MiamCoreUiEvent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeCarouselContractEvent.GetRecipeSuggestionsFromCriteria")))
@interface MiamCoreRecipeCarouselContractEventGetRecipeSuggestionsFromCriteria : MiamCoreRecipeCarouselContractEvent
- (instancetype)initWithCriteria:(MiamCoreSuggestionsCriteria *)criteria numberOfResult:(int32_t)numberOfResult __attribute__((swift_name("init(criteria:numberOfResult:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (MiamCoreSuggestionsCriteria *)component1 __attribute__((swift_name("component1()")));
- (int32_t)component2 __attribute__((swift_name("component2()")));
- (MiamCoreRecipeCarouselContractEventGetRecipeSuggestionsFromCriteria *)doCopyCriteria:(MiamCoreSuggestionsCriteria *)criteria numberOfResult:(int32_t)numberOfResult __attribute__((swift_name("doCopy(criteria:numberOfResult:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreSuggestionsCriteria *criteria __attribute__((swift_name("criteria")));
@property (readonly) int32_t numberOfResult __attribute__((swift_name("numberOfResult")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeCarouselContractEvent.GetRecipeSuggestionsFromId")))
@interface MiamCoreRecipeCarouselContractEventGetRecipeSuggestionsFromId : MiamCoreRecipeCarouselContractEvent
- (instancetype)initWithProductId:(NSString *)productId numberOfResult:(int32_t)numberOfResult __attribute__((swift_name("init(productId:numberOfResult:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (int32_t)component2 __attribute__((swift_name("component2()")));
- (MiamCoreRecipeCarouselContractEventGetRecipeSuggestionsFromId *)doCopyProductId:(NSString *)productId numberOfResult:(int32_t)numberOfResult __attribute__((swift_name("doCopy(productId:numberOfResult:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t numberOfResult __attribute__((swift_name("numberOfResult")));
@property (readonly) NSString *productId __attribute__((swift_name("productId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeCarouselContractState")))
@interface MiamCoreRecipeCarouselContractState : MiamCoreBase <MiamCoreUiState>
- (instancetype)initWithSuggestions:(MiamCoreBasicUiState<NSArray<MiamCoreRecipe *> *> *)suggestions __attribute__((swift_name("init(suggestions:)"))) __attribute__((objc_designated_initializer));
- (MiamCoreBasicUiState<NSArray<MiamCoreRecipe *> *> *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreRecipeCarouselContractState *)doCopySuggestions:(MiamCoreBasicUiState<NSArray<MiamCoreRecipe *> *> *)suggestions __attribute__((swift_name("doCopy(suggestions:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreBasicUiState<NSArray<MiamCoreRecipe *> *> *suggestions __attribute__((swift_name("suggestions")));
@end;

__attribute__((swift_name("RecipeCarouselViewModel")))
@interface MiamCoreRecipeCarouselViewModel : MiamCoreBaseViewModel<MiamCoreRecipeCarouselContractEvent *, MiamCoreRecipeCarouselContractState *, MiamCoreRecipeCarouselContractEffect *>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (MiamCoreRecipeCarouselContractState *)createInitialState __attribute__((swift_name("createInitialState()")));
- (void)handleEventEvent:(MiamCoreRecipeCarouselContractEvent *)event __attribute__((swift_name("handleEvent(event:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FilterViewModelInstance")))
@interface MiamCoreFilterViewModelInstance : MiamCoreBase <MiamCoreKoin_coreKoinComponent>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)filterViewModelInstance __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreFilterViewModelInstance *shared __attribute__((swift_name("shared")));
@property (readonly) MiamCoreSingletonFilterViewModel *instance __attribute__((swift_name("instance")));
@end;

__attribute__((swift_name("SingletonFilterContract")))
@protocol MiamCoreSingletonFilterContract
@required
@end;

__attribute__((swift_name("SingletonFilterContractEffect")))
@interface MiamCoreSingletonFilterContractEffect : MiamCoreBase <MiamCoreUiEffect>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SingletonFilterContractEffect.OnUpdate")))
@interface MiamCoreSingletonFilterContractEffectOnUpdate : MiamCoreSingletonFilterContractEffect
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)onUpdate __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreSingletonFilterContractEffectOnUpdate *shared __attribute__((swift_name("shared")));
@end;

__attribute__((swift_name("SingletonFilterContractEvent")))
@interface MiamCoreSingletonFilterContractEvent : MiamCoreBase <MiamCoreUiEvent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SingletonFilterContractEvent.OnCostFilterChanged")))
@interface MiamCoreSingletonFilterContractEventOnCostFilterChanged : MiamCoreSingletonFilterContractEvent
- (instancetype)initWithCostFilter:(MiamCoreCatalogFilterOptions *)costFilter __attribute__((swift_name("init(costFilter:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (MiamCoreCatalogFilterOptions *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreSingletonFilterContractEventOnCostFilterChanged *)doCopyCostFilter:(MiamCoreCatalogFilterOptions *)costFilter __attribute__((swift_name("doCopy(costFilter:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreCatalogFilterOptions *costFilter __attribute__((swift_name("costFilter")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SingletonFilterContractEvent.OnDifficultyChanged")))
@interface MiamCoreSingletonFilterContractEventOnDifficultyChanged : MiamCoreSingletonFilterContractEvent
- (instancetype)initWithDifficulty:(MiamCoreCatalogFilterOptions *)difficulty __attribute__((swift_name("init(difficulty:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (MiamCoreCatalogFilterOptions *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreSingletonFilterContractEventOnDifficultyChanged *)doCopyDifficulty:(MiamCoreCatalogFilterOptions *)difficulty __attribute__((swift_name("doCopy(difficulty:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreCatalogFilterOptions *difficulty __attribute__((swift_name("difficulty")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SingletonFilterContractEvent.OnTimeFilterChanged")))
@interface MiamCoreSingletonFilterContractEventOnTimeFilterChanged : MiamCoreSingletonFilterContractEvent
- (instancetype)initWithTimeFilter:(MiamCoreCatalogFilterOptions *)timeFilter __attribute__((swift_name("init(timeFilter:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (MiamCoreCatalogFilterOptions *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreSingletonFilterContractEventOnTimeFilterChanged *)doCopyTimeFilter:(MiamCoreCatalogFilterOptions *)timeFilter __attribute__((swift_name("doCopy(timeFilter:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreCatalogFilterOptions *timeFilter __attribute__((swift_name("timeFilter")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SingletonFilterContractState")))
@interface MiamCoreSingletonFilterContractState : MiamCoreBase <MiamCoreUiState>
- (instancetype)initWithNumberOfResult:(int32_t)numberOfResult difficulty:(NSArray<MiamCoreCatalogFilterOptions *> *)difficulty cost:(NSArray<MiamCoreCatalogFilterOptions *> *)cost time:(NSArray<MiamCoreCatalogFilterOptions *> *)time searchString:(NSString * _Nullable)searchString isFavorite:(BOOL)isFavorite category:(NSString * _Nullable)category __attribute__((swift_name("init(numberOfResult:difficulty:cost:time:searchString:isFavorite:category:)"))) __attribute__((objc_designated_initializer));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (NSArray<MiamCoreCatalogFilterOptions *> *)component2 __attribute__((swift_name("component2()")));
- (NSArray<MiamCoreCatalogFilterOptions *> *)component3 __attribute__((swift_name("component3()")));
- (NSArray<MiamCoreCatalogFilterOptions *> *)component4 __attribute__((swift_name("component4()")));
- (NSString * _Nullable)component5 __attribute__((swift_name("component5()")));
- (BOOL)component6 __attribute__((swift_name("component6()")));
- (NSString * _Nullable)component7 __attribute__((swift_name("component7()")));
- (MiamCoreSingletonFilterContractState *)doCopyNumberOfResult:(int32_t)numberOfResult difficulty:(NSArray<MiamCoreCatalogFilterOptions *> *)difficulty cost:(NSArray<MiamCoreCatalogFilterOptions *> *)cost time:(NSArray<MiamCoreCatalogFilterOptions *> *)time searchString:(NSString * _Nullable)searchString isFavorite:(BOOL)isFavorite category:(NSString * _Nullable)category __attribute__((swift_name("doCopy(numberOfResult:difficulty:cost:time:searchString:isFavorite:category:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable category __attribute__((swift_name("category")));
@property (readonly) NSArray<MiamCoreCatalogFilterOptions *> *cost __attribute__((swift_name("cost")));
@property (readonly) NSArray<MiamCoreCatalogFilterOptions *> *difficulty __attribute__((swift_name("difficulty")));
@property (readonly) BOOL isFavorite __attribute__((swift_name("isFavorite")));
@property (readonly) int32_t numberOfResult __attribute__((swift_name("numberOfResult")));
@property (readonly) NSString * _Nullable searchString __attribute__((swift_name("searchString")));
@property (readonly) NSArray<MiamCoreCatalogFilterOptions *> *time __attribute__((swift_name("time")));
@end;

__attribute__((swift_name("SingletonFilterViewModel")))
@interface MiamCoreSingletonFilterViewModel : MiamCoreBaseViewModel<MiamCoreSingletonFilterContractEvent *, MiamCoreSingletonFilterContractState *, MiamCoreSingletonFilterContractEffect *>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreSingletonFilterViewModelCompanion *companion __attribute__((swift_name("companion")));
- (void)applyFilter __attribute__((swift_name("applyFilter()")));
- (void)clear __attribute__((swift_name("clear()")));
- (MiamCoreSingletonFilterContractState *)createInitialState __attribute__((swift_name("createInitialState()")));
- (int32_t)getActiveFilterCount __attribute__((swift_name("getActiveFilterCount()")));
- (void)getRecipeCount __attribute__((swift_name("getRecipeCount()")));
- (NSString *)getSelectedFilterAsQueryString __attribute__((swift_name("getSelectedFilterAsQueryString()")));
- (void)handleEventEvent:(MiamCoreSingletonFilterContractEvent *)event __attribute__((swift_name("handleEvent(event:)")));
- (void)setCatCatId:(NSString *)catId __attribute__((swift_name("setCat(catId:)")));
- (void)setFavorite __attribute__((swift_name("setFavorite()")));
- (void)setSearchStringSearchString:(NSString *)searchString __attribute__((swift_name("setSearchString(searchString:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SingletonFilterViewModel.Companion")))
@interface MiamCoreSingletonFilterViewModelCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreSingletonFilterViewModelCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) MiamCoreSingletonFilterContractState *initialState __attribute__((swift_name("initialState")));
@end;

__attribute__((swift_name("BasketTagContract")))
@protocol MiamCoreBasketTagContract
@required
@end;

__attribute__((swift_name("BasketTagContractEffect")))
@interface MiamCoreBasketTagContractEffect : MiamCoreBase <MiamCoreUiEffect>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((swift_name("BasketTagContractEvent")))
@interface MiamCoreBasketTagContractEvent : MiamCoreBase <MiamCoreUiEvent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketTagContractEvent.SetRetailerProductId")))
@interface MiamCoreBasketTagContractEventSetRetailerProductId : MiamCoreBasketTagContractEvent
- (instancetype)initWithProductId:(NSString *)productId __attribute__((swift_name("init(productId:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreBasketTagContractEventSetRetailerProductId *)doCopyProductId:(NSString *)productId __attribute__((swift_name("doCopy(productId:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *productId __attribute__((swift_name("productId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketTagContractState")))
@interface MiamCoreBasketTagContractState : MiamCoreBase <MiamCoreUiState>
- (instancetype)initWithRecipeList:(MiamCoreBasicUiState<NSArray<MiamCoreRecipe *> *> *)recipeList __attribute__((swift_name("init(recipeList:)"))) __attribute__((objc_designated_initializer));
- (MiamCoreBasicUiState<NSArray<MiamCoreRecipe *> *> *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreBasketTagContractState *)doCopyRecipeList:(MiamCoreBasicUiState<NSArray<MiamCoreRecipe *> *> *)recipeList __attribute__((swift_name("doCopy(recipeList:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreBasicUiState<NSArray<MiamCoreRecipe *> *> *recipeList __attribute__((swift_name("recipeList")));
@end;

__attribute__((swift_name("BasketTagViewModel")))
@interface MiamCoreBasketTagViewModel : MiamCoreBaseViewModel<MiamCoreBasketTagContractEvent *, MiamCoreBasketTagContractState *, MiamCoreBasketTagContractEffect *>
- (instancetype)initWithVmRouter:(MiamCoreRouterOutletViewModel *)vmRouter __attribute__((swift_name("init(vmRouter:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (MiamCoreBasketTagContractState *)createInitialState __attribute__((swift_name("createInitialState()")));
- (void)goToDetailRecipe:(MiamCoreRecipe *)recipe __attribute__((swift_name("goToDetail(recipe:)")));
- (void)handleEventEvent:(MiamCoreBasketTagContractEvent *)event __attribute__((swift_name("handleEvent(event:)")));
@end;

__attribute__((swift_name("RecipeContract")))
@protocol MiamCoreRecipeContract
@required
@end;

__attribute__((swift_name("RecipeContractEffect")))
@interface MiamCoreRecipeContractEffect : MiamCoreBase <MiamCoreUiEffect>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeContractEffect.Disliked")))
@interface MiamCoreRecipeContractEffectDisliked : MiamCoreRecipeContractEffect
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)disliked __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeContractEffectDisliked *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeContractEffect.HideCard")))
@interface MiamCoreRecipeContractEffectHideCard : MiamCoreRecipeContractEffect
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)hideCard __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeContractEffectHideCard *shared __attribute__((swift_name("shared")));
@end;

__attribute__((swift_name("RecipeContractEvent")))
@interface MiamCoreRecipeContractEvent : MiamCoreBase <MiamCoreUiEvent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeContractEvent.Error")))
@interface MiamCoreRecipeContractEventError : MiamCoreRecipeContractEvent
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)error __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeContractEventError *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeContractEvent.OnAddRecipe")))
@interface MiamCoreRecipeContractEventOnAddRecipe : MiamCoreRecipeContractEvent
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)onAddRecipe __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeContractEventOnAddRecipe *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeContractEvent.SetActiveStep")))
@interface MiamCoreRecipeContractEventSetActiveStep : MiamCoreRecipeContractEvent
- (instancetype)initWithStepIndex:(int32_t)stepIndex __attribute__((swift_name("init(stepIndex:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (MiamCoreRecipeContractEventSetActiveStep *)doCopyStepIndex:(int32_t)stepIndex __attribute__((swift_name("doCopy(stepIndex:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t stepIndex __attribute__((swift_name("stepIndex")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeContractEvent.ShowIngredient")))
@interface MiamCoreRecipeContractEventShowIngredient : MiamCoreRecipeContractEvent
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)showIngredient __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeContractEventShowIngredient *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeContractEvent.ShowSteps")))
@interface MiamCoreRecipeContractEventShowSteps : MiamCoreRecipeContractEvent
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)showSteps __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreRecipeContractEventShowSteps *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecipeContractState")))
@interface MiamCoreRecipeContractState : MiamCoreBase <MiamCoreUiState>
- (instancetype)initWithRecipeState:(MiamCoreBasicUiState<MiamCoreRecipe *> *)recipeState recipe:(MiamCoreRecipe * _Nullable)recipe headerText:(NSString *)headerText guest:(int32_t)guest guestUpdating:(BOOL)guestUpdating isInCart:(BOOL)isInCart analyticsEventSent:(BOOL)analyticsEventSent isPriceDisplayed:(BOOL)isPriceDisplayed isInViewport:(BOOL)isInViewport tabState:(MiamCoreTabEnum *)tabState activeStep:(int32_t)activeStep recipeLoaded:(BOOL)recipeLoaded likeIsEnable:(BOOL)likeIsEnable show_event_sent:(BOOL)show_event_sent __attribute__((swift_name("init(recipeState:recipe:headerText:guest:guestUpdating:isInCart:analyticsEventSent:isPriceDisplayed:isInViewport:tabState:activeStep:recipeLoaded:likeIsEnable:show_event_sent:)"))) __attribute__((objc_designated_initializer));
- (MiamCoreBasicUiState<MiamCoreRecipe *> *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreTabEnum *)component10 __attribute__((swift_name("component10()")));
- (int32_t)component11 __attribute__((swift_name("component11()")));
- (BOOL)component12 __attribute__((swift_name("component12()")));
- (BOOL)component13 __attribute__((swift_name("component13()")));
- (BOOL)component14 __attribute__((swift_name("component14()")));
- (MiamCoreRecipe * _Nullable)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (int32_t)component4 __attribute__((swift_name("component4()")));
- (BOOL)component5 __attribute__((swift_name("component5()")));
- (BOOL)component6 __attribute__((swift_name("component6()")));
- (BOOL)component7 __attribute__((swift_name("component7()")));
- (BOOL)component8 __attribute__((swift_name("component8()")));
- (BOOL)component9 __attribute__((swift_name("component9()")));
- (MiamCoreRecipeContractState *)doCopyRecipeState:(MiamCoreBasicUiState<MiamCoreRecipe *> *)recipeState recipe:(MiamCoreRecipe * _Nullable)recipe headerText:(NSString *)headerText guest:(int32_t)guest guestUpdating:(BOOL)guestUpdating isInCart:(BOOL)isInCart analyticsEventSent:(BOOL)analyticsEventSent isPriceDisplayed:(BOOL)isPriceDisplayed isInViewport:(BOOL)isInViewport tabState:(MiamCoreTabEnum *)tabState activeStep:(int32_t)activeStep recipeLoaded:(BOOL)recipeLoaded likeIsEnable:(BOOL)likeIsEnable show_event_sent:(BOOL)show_event_sent __attribute__((swift_name("doCopy(recipeState:recipe:headerText:guest:guestUpdating:isInCart:analyticsEventSent:isPriceDisplayed:isInViewport:tabState:activeStep:recipeLoaded:likeIsEnable:show_event_sent:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (MiamCoreRecipeContractState *)refreshFromGlGroceriesListStore:(MiamCoreGroceriesListStore *)groceriesListStore __attribute__((swift_name("refreshFromGl(groceriesListStore:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t activeStep __attribute__((swift_name("activeStep")));
@property (readonly) BOOL analyticsEventSent __attribute__((swift_name("analyticsEventSent")));
@property (readonly) int32_t guest __attribute__((swift_name("guest")));
@property (readonly) BOOL guestUpdating __attribute__((swift_name("guestUpdating")));
@property (readonly) NSString *headerText __attribute__((swift_name("headerText")));
@property (readonly) BOOL isInCart __attribute__((swift_name("isInCart")));
@property (readonly) BOOL isInViewport __attribute__((swift_name("isInViewport")));
@property (readonly) BOOL isPriceDisplayed __attribute__((swift_name("isPriceDisplayed")));
@property (readonly) BOOL likeIsEnable __attribute__((swift_name("likeIsEnable")));
@property (readonly) MiamCoreRecipe * _Nullable recipe __attribute__((swift_name("recipe")));
@property (readonly) BOOL recipeLoaded __attribute__((swift_name("recipeLoaded")));
@property (readonly) MiamCoreBasicUiState<MiamCoreRecipe *> *recipeState __attribute__((swift_name("recipeState")));
@property (readonly) BOOL show_event_sent __attribute__((swift_name("show_event_sent")));
@property (readonly) MiamCoreTabEnum *tabState __attribute__((swift_name("tabState")));
@end;

__attribute__((swift_name("RecipeViewModel")))
@interface MiamCoreRecipeViewModel : MiamCoreBaseViewModel<MiamCoreRecipeContractEvent *, MiamCoreRecipeContractState *, MiamCoreRecipeContractEffect *>
- (instancetype)initWithRouterVM:(MiamCoreRouterOutletViewModel *)routerVM __attribute__((swift_name("init(routerVM:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (MiamCoreRecipeContractState *)createInitialState __attribute__((swift_name("createInitialState()")));
- (void)fetchRecipeRecipeId:(NSString *)recipeId __attribute__((swift_name("fetchRecipe(recipeId:)")));
- (void)goToDetail __attribute__((swift_name("goToDetail()")));
- (void)handleEventEvent:(MiamCoreRecipeContractEvent *)event __attribute__((swift_name("handleEvent(event:)")));
- (void)setRecipeRecipe:(MiamCoreRecipe *)recipe __attribute__((swift_name("setRecipe(recipe:)")));
- (void)setRecipeFromSuggestionCriteria:(MiamCoreSuggestionsCriteria *)criteria __attribute__((swift_name("setRecipeFromSuggestion(criteria:)")));
- (void)unsetRecipe __attribute__((swift_name("unsetRecipe()")));
- (void)updateGuestNbGuest:(int32_t)nbGuest __attribute__((swift_name("updateGuest(nbGuest:)")));
@property (readonly) NSString * _Nullable recipeId __attribute__((swift_name("recipeId")));
@property (readonly) MiamCoreRouterOutletViewModel *routerVM __attribute__((swift_name("routerVM")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TabEnum")))
@interface MiamCoreTabEnum : MiamCoreKotlinEnum<MiamCoreTabEnum *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) MiamCoreTabEnum *ingredient __attribute__((swift_name("ingredient")));
@property (class, readonly) MiamCoreTabEnum *step __attribute__((swift_name("step")));
+ (MiamCoreKotlinArray<MiamCoreTabEnum *> *)values __attribute__((swift_name("values()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CatalogContent")))
@interface MiamCoreCatalogContent : MiamCoreKotlinEnum<MiamCoreCatalogContent *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) MiamCoreCatalogContent *wordSearch __attribute__((swift_name("wordSearch")));
@property (class, readonly) MiamCoreCatalogContent *filterSearch __attribute__((swift_name("filterSearch")));
@property (class, readonly) MiamCoreCatalogContent *categoriesList __attribute__((swift_name("categoriesList")));
@property (class, readonly) MiamCoreCatalogContent *category __attribute__((swift_name("category")));
@property (class, readonly) MiamCoreCatalogContent *favorite __attribute__((swift_name("favorite")));
+ (MiamCoreKotlinArray<MiamCoreCatalogContent *> *)values __attribute__((swift_name("values()")));
@end;

__attribute__((swift_name("CatalogContract")))
@protocol MiamCoreCatalogContract
@required
@end;

__attribute__((swift_name("CatalogContractEffect")))
@interface MiamCoreCatalogContractEffect : MiamCoreBase <MiamCoreUiEffect>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((swift_name("CatalogContractEvent")))
@interface MiamCoreCatalogContractEvent : MiamCoreBase <MiamCoreUiEvent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CatalogContractEvent.GoBack")))
@interface MiamCoreCatalogContractEventGoBack : MiamCoreCatalogContractEvent
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)goBack __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreCatalogContractEventGoBack *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CatalogContractEvent.GoToFavorite")))
@interface MiamCoreCatalogContractEventGoToFavorite : MiamCoreCatalogContractEvent
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)goToFavorite __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreCatalogContractEventGoToFavorite *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CatalogContractState")))
@interface MiamCoreCatalogContractState : MiamCoreBase <MiamCoreUiState>
- (instancetype)initWithCategories:(MiamCoreBasicUiState<NSArray<MiamCorePackage *> *> *)categories content:(MiamCoreCatalogContent *)content dialogIsOpen:(BOOL)dialogIsOpen dialogContent:(MiamCoreDialogContent *)dialogContent enableFilters:(BOOL)enableFilters enablePreferences:(BOOL)enablePreferences openedCategoryId:(NSString *)openedCategoryId openedCategoryTitle:(NSString *)openedCategoryTitle __attribute__((swift_name("init(categories:content:dialogIsOpen:dialogContent:enableFilters:enablePreferences:openedCategoryId:openedCategoryTitle:)"))) __attribute__((objc_designated_initializer));
- (MiamCoreBasicUiState<NSArray<MiamCorePackage *> *> *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreCatalogContent *)component2 __attribute__((swift_name("component2()")));
- (BOOL)component3 __attribute__((swift_name("component3()")));
- (MiamCoreDialogContent *)component4 __attribute__((swift_name("component4()")));
- (BOOL)component5 __attribute__((swift_name("component5()")));
- (BOOL)component6 __attribute__((swift_name("component6()")));
- (NSString *)component7 __attribute__((swift_name("component7()")));
- (NSString *)component8 __attribute__((swift_name("component8()")));
- (MiamCoreCatalogContractState *)doCopyCategories:(MiamCoreBasicUiState<NSArray<MiamCorePackage *> *> *)categories content:(MiamCoreCatalogContent *)content dialogIsOpen:(BOOL)dialogIsOpen dialogContent:(MiamCoreDialogContent *)dialogContent enableFilters:(BOOL)enableFilters enablePreferences:(BOOL)enablePreferences openedCategoryId:(NSString *)openedCategoryId openedCategoryTitle:(NSString *)openedCategoryTitle __attribute__((swift_name("doCopy(categories:content:dialogIsOpen:dialogContent:enableFilters:enablePreferences:openedCategoryId:openedCategoryTitle:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreBasicUiState<NSArray<MiamCorePackage *> *> *categories __attribute__((swift_name("categories")));
@property (readonly) MiamCoreCatalogContent *content __attribute__((swift_name("content")));
@property (readonly) MiamCoreDialogContent *dialogContent __attribute__((swift_name("dialogContent")));
@property (readonly) BOOL dialogIsOpen __attribute__((swift_name("dialogIsOpen")));
@property (readonly) BOOL enableFilters __attribute__((swift_name("enableFilters")));
@property (readonly) BOOL enablePreferences __attribute__((swift_name("enablePreferences")));
@property (readonly) NSString *openedCategoryId __attribute__((swift_name("openedCategoryId")));
@property (readonly) NSString *openedCategoryTitle __attribute__((swift_name("openedCategoryTitle")));
@end;

__attribute__((swift_name("CatalogViewModel")))
@interface MiamCoreCatalogViewModel : MiamCoreBaseViewModel<MiamCoreCatalogContractEvent *, MiamCoreCatalogContractState *, MiamCoreCatalogContractEffect *>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreCatalogViewModelCompanion *companion __attribute__((swift_name("companion")));
- (void)close __attribute__((swift_name("close()")));
- (MiamCoreCatalogContractState *)createInitialState __attribute__((swift_name("createInitialState()")));
- (void)enableFiltersEnable:(BOOL)enable __attribute__((swift_name("enableFilters(enable:)")));
- (void)enablePreferencesEnable:(BOOL)enable __attribute__((swift_name("enablePreferences(enable:)")));
- (void)goToCategoriesList __attribute__((swift_name("goToCategoriesList()")));
- (void)goToCategoryCategoryId:(NSString *)categoryId categoryTitle:(NSString *)categoryTitle __attribute__((swift_name("goToCategory(categoryId:categoryTitle:)")));
- (void)handleEventEvent:(MiamCoreCatalogContractEvent *)event __attribute__((swift_name("handleEvent(event:)")));
- (void)onSimpleSearchContent:(MiamCoreCatalogContent *)content __attribute__((swift_name("onSimpleSearch(content:)")));
- (void)openFilter __attribute__((swift_name("openFilter()")));
- (void)openPreferences __attribute__((swift_name("openPreferences()")));
- (void)openSearch __attribute__((swift_name("openSearch()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CatalogViewModel.Companion")))
@interface MiamCoreCatalogViewModelCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreCatalogViewModelCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) NSString *CATEGORIES_LIST_TITLE __attribute__((swift_name("CATEGORIES_LIST_TITLE")));
@property (readonly) NSString *FAVORITE_LIST_TITLE __attribute__((swift_name("FAVORITE_LIST_TITLE")));
@property (readonly) NSString *FILTER_LIST_TITLE __attribute__((swift_name("FILTER_LIST_TITLE")));
@property (readonly) NSString *RECIPE_LIST_TITLE __attribute__((swift_name("RECIPE_LIST_TITLE")));
@property (readonly) NSString *SEARCH_LIST_TITLE __attribute__((swift_name("SEARCH_LIST_TITLE")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DialogContent")))
@interface MiamCoreDialogContent : MiamCoreKotlinEnum<MiamCoreDialogContent *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) MiamCoreDialogContent *filter __attribute__((swift_name("filter")));
@property (class, readonly) MiamCoreDialogContent *preferences __attribute__((swift_name("preferences")));
@property (class, readonly) MiamCoreDialogContent *search __attribute__((swift_name("search")));
+ (MiamCoreKotlinArray<MiamCoreDialogContent *> *)values __attribute__((swift_name("values()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AlterQuantityBasketEntry")))
@interface MiamCoreAlterQuantityBasketEntry : MiamCoreBase
- (instancetype)initWithId:(NSString *)id delatQty:(int32_t)delatQty __attribute__((swift_name("init(id:delatQty:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (int32_t)component2 __attribute__((swift_name("component2()")));
- (MiamCoreAlterQuantityBasketEntry *)doCopyId:(NSString *)id delatQty:(int32_t)delatQty __attribute__((swift_name("doCopy(id:delatQty:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t delatQty __attribute__((swift_name("delatQty")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@end;

__attribute__((swift_name("BasicUiState")))
@interface MiamCoreBasicUiState<__covariant T> : MiamCoreBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasicUiStateEmpty")))
@interface MiamCoreBasicUiStateEmpty : MiamCoreBasicUiState<MiamCoreKotlinNothing *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)empty __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreBasicUiStateEmpty *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasicUiStateError")))
@interface MiamCoreBasicUiStateError : MiamCoreBasicUiState<MiamCoreKotlinNothing *>
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (NSString * _Nullable)component1 __attribute__((swift_name("component1()")));
- (MiamCoreBasicUiStateError *)doCopyMessage:(NSString * _Nullable)message __attribute__((swift_name("doCopy(message:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasicUiStateIdle")))
@interface MiamCoreBasicUiStateIdle : MiamCoreBasicUiState<MiamCoreKotlinNothing *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)idle __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreBasicUiStateIdle *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasicUiStateLoading")))
@interface MiamCoreBasicUiStateLoading : MiamCoreBasicUiState<MiamCoreKotlinNothing *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)loading __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreBasicUiStateLoading *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasicUiStateSuccess")))
@interface MiamCoreBasicUiStateSuccess<T> : MiamCoreBasicUiState<T>
- (instancetype)initWithData:(T _Nullable)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (T _Nullable)component1 __attribute__((swift_name("component1()")));
- (MiamCoreBasicUiStateSuccess<T> *)doCopyData:(T _Nullable)data __attribute__((swift_name("doCopy(data:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) T _Nullable data __attribute__((swift_name("data")));
@end;

__attribute__((swift_name("BasketAction")))
@interface MiamCoreBasketAction : MiamCoreBase <MiamCoreAction>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketAction.AddBasketEntry")))
@interface MiamCoreBasketActionAddBasketEntry : MiamCoreBasketAction
- (instancetype)initWithEntry:(MiamCoreBasketEntry *)entry __attribute__((swift_name("init(entry:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (MiamCoreBasketEntry *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreBasketActionAddBasketEntry *)doCopyEntry:(MiamCoreBasketEntry *)entry __attribute__((swift_name("doCopy(entry:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreBasketEntry *entry __attribute__((swift_name("entry")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketAction.ConfirmBasket")))
@interface MiamCoreBasketActionConfirmBasket : MiamCoreBasketAction
- (instancetype)initWithPrice:(NSString *)price __attribute__((swift_name("init(price:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreBasketActionConfirmBasket *)doCopyPrice:(NSString *)price __attribute__((swift_name("doCopy(price:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *price __attribute__((swift_name("price")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketAction.RefreshBasket")))
@interface MiamCoreBasketActionRefreshBasket : MiamCoreBasketAction
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)refreshBasket __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreBasketActionRefreshBasket *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketAction.RemoveEntry")))
@interface MiamCoreBasketActionRemoveEntry : MiamCoreBasketAction
- (instancetype)initWithEntry:(MiamCoreBasketEntry *)entry __attribute__((swift_name("init(entry:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (MiamCoreBasketEntry *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreBasketActionRemoveEntry *)doCopyEntry:(MiamCoreBasketEntry *)entry __attribute__((swift_name("doCopy(entry:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreBasketEntry *entry __attribute__((swift_name("entry")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketAction.ReplaceSelectedItem")))
@interface MiamCoreBasketActionReplaceSelectedItem : MiamCoreBasketAction
- (instancetype)initWithBasketEntry:(MiamCoreBasketEntry *)basketEntry itemId:(int32_t)itemId __attribute__((swift_name("init(basketEntry:itemId:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (MiamCoreBasketEntry *)component1 __attribute__((swift_name("component1()")));
- (int32_t)component2 __attribute__((swift_name("component2()")));
- (MiamCoreBasketActionReplaceSelectedItem *)doCopyBasketEntry:(MiamCoreBasketEntry *)basketEntry itemId:(int32_t)itemId __attribute__((swift_name("doCopy(basketEntry:itemId:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreBasketEntry *basketEntry __attribute__((swift_name("basketEntry")));
@property (readonly) int32_t itemId __attribute__((swift_name("itemId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketAction.UpdateBasketEntries")))
@interface MiamCoreBasketActionUpdateBasketEntries : MiamCoreBasketAction
- (instancetype)initWithBasketEntries:(NSArray<MiamCoreBasketEntry *> *)basketEntries __attribute__((swift_name("init(basketEntries:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (NSArray<MiamCoreBasketEntry *> *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreBasketActionUpdateBasketEntries *)doCopyBasketEntries:(NSArray<MiamCoreBasketEntry *> *)basketEntries __attribute__((swift_name("doCopy(basketEntries:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<MiamCoreBasketEntry *> *basketEntries __attribute__((swift_name("basketEntries")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketAction.UpdateBasketEntriesDiff")))
@interface MiamCoreBasketActionUpdateBasketEntriesDiff : MiamCoreBasketAction
- (instancetype)initWithBasketEntriesDiff:(NSArray<MiamCoreAlterQuantityBasketEntry *> *)basketEntriesDiff __attribute__((swift_name("init(basketEntriesDiff:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (NSArray<MiamCoreAlterQuantityBasketEntry *> *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreBasketActionUpdateBasketEntriesDiff *)doCopyBasketEntriesDiff:(NSArray<MiamCoreAlterQuantityBasketEntry *> *)basketEntriesDiff __attribute__((swift_name("doCopy(basketEntriesDiff:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<MiamCoreAlterQuantityBasketEntry *> *basketEntriesDiff __attribute__((swift_name("basketEntriesDiff")));
@end;

__attribute__((swift_name("BasketEffect")))
@interface MiamCoreBasketEffect : MiamCoreBase <MiamCoreEffect>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketEffect.BasketConfirmed")))
@interface MiamCoreBasketEffectBasketConfirmed : MiamCoreBasketEffect
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)basketConfirmed __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreBasketEffectBasketConfirmed *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketEffect.BasketPreviewChange")))
@interface MiamCoreBasketEffectBasketPreviewChange : MiamCoreBasketEffect
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)basketPreviewChange __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreBasketEffectBasketPreviewChange *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketState")))
@interface MiamCoreBasketState : MiamCoreBase <MiamCoreState>
- (instancetype)initWithBasket:(MiamCoreBasket * _Nullable)basket basketPreview:(NSArray<MiamCoreBasketPreviewLine *> * _Nullable)basketPreview entriesCount:(MiamCoreInt * _Nullable)entriesCount recipeCount:(MiamCoreInt * _Nullable)recipeCount totalPrice:(MiamCoreDouble * _Nullable)totalPrice __attribute__((swift_name("init(basket:basketPreview:entriesCount:recipeCount:totalPrice:)"))) __attribute__((objc_designated_initializer));
- (MiamCoreBasket * _Nullable)component1 __attribute__((swift_name("component1()")));
- (NSArray<MiamCoreBasketPreviewLine *> * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreInt * _Nullable)component3 __attribute__((swift_name("component3()")));
- (MiamCoreInt * _Nullable)component4 __attribute__((swift_name("component4()")));
- (MiamCoreDouble * _Nullable)component5 __attribute__((swift_name("component5()")));
- (MiamCoreBasketState *)doCopyBasket:(MiamCoreBasket * _Nullable)basket basketPreview:(NSArray<MiamCoreBasketPreviewLine *> * _Nullable)basketPreview entriesCount:(MiamCoreInt * _Nullable)entriesCount recipeCount:(MiamCoreInt * _Nullable)recipeCount totalPrice:(MiamCoreDouble * _Nullable)totalPrice __attribute__((swift_name("doCopy(basket:basketPreview:entriesCount:recipeCount:totalPrice:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreBasket * _Nullable basket __attribute__((swift_name("basket")));
@property (readonly) NSArray<MiamCoreBasketPreviewLine *> * _Nullable basketPreview __attribute__((swift_name("basketPreview")));
@property (readonly) MiamCoreInt * _Nullable entriesCount __attribute__((swift_name("entriesCount")));
@property (readonly) MiamCoreInt * _Nullable recipeCount __attribute__((swift_name("recipeCount")));
@property (readonly) MiamCoreDouble * _Nullable totalPrice __attribute__((swift_name("totalPrice")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketStore")))
@interface MiamCoreBasketStore : MiamCoreBase <MiamCoreStore, MiamCoreKoin_coreKoinComponent, MiamCoreKotlinx_coroutines_coreCoroutineScope>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (NSArray<MiamCoreBasketEntry *> * _Nullable)activeEntries __attribute__((swift_name("activeEntries()")));
- (BOOL)basketIsEmpty __attribute__((swift_name("basketIsEmpty()")));
- (id<MiamCoreKotlinx_coroutines_coreJob>)dispatchAction:(MiamCoreBasketAction *)action __attribute__((swift_name("dispatch(action:)")));
- (void)fastRemoveRecipeFromBplRecipeId:(NSString *)recipeId __attribute__((swift_name("fastRemoveRecipeFromBpl(recipeId:)")));
- (MiamCoreBasket * _Nullable)getBasket __attribute__((swift_name("getBasket()")));
- (id<MiamCoreKotlinx_coroutines_coreFlow>)observeSideEffect __attribute__((swift_name("observeSideEffect()")));
- (id<MiamCoreKotlinx_coroutines_coreStateFlow>)observeState __attribute__((swift_name("observeState()")));
- (BOOL)recipeInBasketRecipeId:(NSString *)recipeId __attribute__((swift_name("recipeInBasket(recipeId:)")));
@property (readonly) id<MiamCoreKotlinCoroutineContext> coroutineContext __attribute__((swift_name("coroutineContext")));
@property (readonly) id<MiamCoreKotlinx_coroutines_coreMutableStateFlow> state __attribute__((swift_name("state")));
@end;

__attribute__((swift_name("GroceriesListAction")))
@interface MiamCoreGroceriesListAction : MiamCoreBase <MiamCoreAction>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesListAction.AlterRecipeList")))
@interface MiamCoreGroceriesListActionAlterRecipeList : MiamCoreGroceriesListAction
- (instancetype)initWithRecipeId:(NSString *)recipeId guests:(int32_t)guests __attribute__((swift_name("init(recipeId:guests:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (int32_t)component2 __attribute__((swift_name("component2()")));
- (MiamCoreGroceriesListActionAlterRecipeList *)doCopyRecipeId:(NSString *)recipeId guests:(int32_t)guests __attribute__((swift_name("doCopy(recipeId:guests:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t guests __attribute__((swift_name("guests")));
@property (readonly) NSString *recipeId __attribute__((swift_name("recipeId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesListAction.RefreshGroceriesList")))
@interface MiamCoreGroceriesListActionRefreshGroceriesList : MiamCoreGroceriesListAction
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)refreshGroceriesList __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreGroceriesListActionRefreshGroceriesList *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesListAction.RemoveRecipe")))
@interface MiamCoreGroceriesListActionRemoveRecipe : MiamCoreGroceriesListAction
- (instancetype)initWithRecipeId:(NSString *)recipeId __attribute__((swift_name("init(recipeId:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreGroceriesListActionRemoveRecipe *)doCopyRecipeId:(NSString *)recipeId __attribute__((swift_name("doCopy(recipeId:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *recipeId __attribute__((swift_name("recipeId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesListAction.ResetGroceriesList")))
@interface MiamCoreGroceriesListActionResetGroceriesList : MiamCoreGroceriesListAction
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)resetGroceriesList __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreGroceriesListActionResetGroceriesList *shared __attribute__((swift_name("shared")));
@end;

__attribute__((swift_name("GroceriesListEffect")))
@interface MiamCoreGroceriesListEffect : MiamCoreBase <MiamCoreEffect>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesListEffect.GroceriesListLoaded")))
@interface MiamCoreGroceriesListEffectGroceriesListLoaded : MiamCoreGroceriesListEffect
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)groceriesListLoaded __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreGroceriesListEffectGroceriesListLoaded *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesListEffect.RecipeAdded")))
@interface MiamCoreGroceriesListEffectRecipeAdded : MiamCoreGroceriesListEffect
- (instancetype)initWithRecipeId:(NSString *)recipeId guests:(int32_t)guests __attribute__((swift_name("init(recipeId:guests:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (int32_t)component2 __attribute__((swift_name("component2()")));
- (MiamCoreGroceriesListEffectRecipeAdded *)doCopyRecipeId:(NSString *)recipeId guests:(int32_t)guests __attribute__((swift_name("doCopy(recipeId:guests:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t guests __attribute__((swift_name("guests")));
@property (readonly) NSString *recipeId __attribute__((swift_name("recipeId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesListEffect.RecipeCountChanged")))
@interface MiamCoreGroceriesListEffectRecipeCountChanged : MiamCoreGroceriesListEffect
- (instancetype)initWithNewRecipeCount:(int32_t)newRecipeCount __attribute__((swift_name("init(newRecipeCount:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (MiamCoreGroceriesListEffectRecipeCountChanged *)doCopyNewRecipeCount:(int32_t)newRecipeCount __attribute__((swift_name("doCopy(newRecipeCount:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly, getter=doNewRecipeCount) int32_t newRecipeCount __attribute__((swift_name("newRecipeCount")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesListEffect.RecipeRemoved")))
@interface MiamCoreGroceriesListEffectRecipeRemoved : MiamCoreGroceriesListEffect
- (instancetype)initWithRecipeId:(NSString *)recipeId __attribute__((swift_name("init(recipeId:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreGroceriesListEffectRecipeRemoved *)doCopyRecipeId:(NSString *)recipeId __attribute__((swift_name("doCopy(recipeId:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *recipeId __attribute__((swift_name("recipeId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesListState")))
@interface MiamCoreGroceriesListState : MiamCoreBase <MiamCoreState>
- (instancetype)initWithGroceriesList:(MiamCoreGroceriesList * _Nullable)groceriesList __attribute__((swift_name("init(groceriesList:)"))) __attribute__((objc_designated_initializer));
- (MiamCoreGroceriesList * _Nullable)component1 __attribute__((swift_name("component1()")));
- (MiamCoreGroceriesListState *)doCopyGroceriesList:(MiamCoreGroceriesList * _Nullable)groceriesList __attribute__((swift_name("doCopy(groceriesList:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreGroceriesList * _Nullable groceriesList __attribute__((swift_name("groceriesList")));
@property (readonly) int32_t recipeCount __attribute__((swift_name("recipeCount")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesListStore")))
@interface MiamCoreGroceriesListStore : MiamCoreBase <MiamCoreStore, MiamCoreKoin_coreKoinComponent, MiamCoreKotlinx_coroutines_coreCoroutineScope>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (id<MiamCoreKotlinx_coroutines_coreJob>)dispatchAction:(MiamCoreGroceriesListAction *)action __attribute__((swift_name("dispatch(action:)")));
- (MiamCoreGroceriesList * _Nullable)getGroceriesList __attribute__((swift_name("getGroceriesList()")));
- (id<MiamCoreKotlinx_coroutines_coreFlow>)observeSideEffect __attribute__((swift_name("observeSideEffect()")));
- (id<MiamCoreKotlinx_coroutines_coreStateFlow>)observeState __attribute__((swift_name("observeState()")));
- (void)updateStateIfChangedNewState:(MiamCoreGroceriesListState *)newState __attribute__((swift_name("updateStateIfChanged(newState:)")));
@property (readonly) id<MiamCoreKotlinCoroutineContext> coroutineContext __attribute__((swift_name("coroutineContext")));
@property (readonly) id<MiamCoreKotlinx_coroutines_coreMutableStateFlow> state __attribute__((swift_name("state")));
@end;

__attribute__((swift_name("LikeEffect")))
@interface MiamCoreLikeEffect : MiamCoreBase <MiamCoreEffect>
- (instancetype)initWithRecipeId:(NSString *)recipeId __attribute__((swift_name("init(recipeId:)"))) __attribute__((objc_designated_initializer));
@property (readonly) NSString *recipeId __attribute__((swift_name("recipeId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("LikeEffect.Disliked")))
@interface MiamCoreLikeEffectDisliked : MiamCoreLikeEffect
- (instancetype)initWithRecipeId:(NSString *)recipeId __attribute__((swift_name("init(recipeId:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreLikeEffectDisliked *)doCopyRecipeId:(NSString *)recipeId __attribute__((swift_name("doCopy(recipeId:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *recipeId __attribute__((swift_name("recipeId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("LikeEffect.Liked")))
@interface MiamCoreLikeEffectLiked : MiamCoreLikeEffect
- (instancetype)initWithRecipe:(MiamCoreRecipe *)recipe __attribute__((swift_name("init(recipe:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithRecipeId:(NSString *)recipeId __attribute__((swift_name("init(recipeId:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (MiamCoreRecipe *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreLikeEffectLiked *)doCopyRecipe:(MiamCoreRecipe *)recipe __attribute__((swift_name("doCopy(recipe:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreRecipe *recipe __attribute__((swift_name("recipe")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("LikeStore")))
@interface MiamCoreLikeStore : MiamCoreBase <MiamCoreKoin_coreKoinComponent, MiamCoreKotlinx_coroutines_coreCoroutineScope>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)fetchAndGetRecipeLikesRecipeIds:(NSArray<NSString *> *)recipeIds completionHandler:(void (^)(NSArray<MiamCoreRecipeLike *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("fetchAndGetRecipeLikes(recipeIds:completionHandler:)")));
- (id<MiamCoreKotlinx_coroutines_coreFlow>)observeSideEffect __attribute__((swift_name("observeSideEffect()")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)toggleLikeRecipeId:(NSString *)recipeId completionHandler:(void (^)(MiamCoreRecipeLike * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("toggleLike(recipeId:completionHandler:)")));
@property (readonly) id<MiamCoreKotlinCoroutineContext> coroutineContext __attribute__((swift_name("coroutineContext")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("LikeStoreInstance")))
@interface MiamCoreLikeStoreInstance : MiamCoreBase <MiamCoreKoin_coreKoinComponent>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)likeStoreInstance __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreLikeStoreInstance *shared __attribute__((swift_name("shared")));
@property (readonly) MiamCoreLikeStore *instance __attribute__((swift_name("instance")));
@end;

__attribute__((swift_name("PointOfSaleAction")))
@interface MiamCorePointOfSaleAction : MiamCoreBase <MiamCoreAction>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PointOfSaleAction.SetExtId")))
@interface MiamCorePointOfSaleActionSetExtId : MiamCorePointOfSaleAction
- (instancetype)initWithExtId:(NSString * _Nullable)extId __attribute__((swift_name("init(extId:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (NSString * _Nullable)component1 __attribute__((swift_name("component1()")));
- (MiamCorePointOfSaleActionSetExtId *)doCopyExtId:(NSString * _Nullable)extId __attribute__((swift_name("doCopy(extId:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable extId __attribute__((swift_name("extId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PointOfSaleAction.SetSupplierId")))
@interface MiamCorePointOfSaleActionSetSupplierId : MiamCorePointOfSaleAction
- (instancetype)initWithSupplierId:(int32_t)supplierId __attribute__((swift_name("init(supplierId:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (MiamCorePointOfSaleActionSetSupplierId *)doCopySupplierId:(int32_t)supplierId __attribute__((swift_name("doCopy(supplierId:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t supplierId __attribute__((swift_name("supplierId")));
@end;

__attribute__((swift_name("PointOfSaleEffect")))
@interface MiamCorePointOfSaleEffect : MiamCoreBase <MiamCoreEffect>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PointOfSaleState")))
@interface MiamCorePointOfSaleState : MiamCoreBase <MiamCoreState>
- (instancetype)initWithIdSupplier:(MiamCoreInt * _Nullable)idSupplier extIdPointOfSale:(NSString * _Nullable)extIdPointOfSale idPointOfSale:(MiamCoreInt * _Nullable)idPointOfSale origin:(NSString * _Nullable)origin currentJob:(id<MiamCoreKotlinx_coroutines_coreJob> _Nullable)currentJob __attribute__((swift_name("init(idSupplier:extIdPointOfSale:idPointOfSale:origin:currentJob:)"))) __attribute__((objc_designated_initializer));
- (MiamCoreInt * _Nullable)component1 __attribute__((swift_name("component1()")));
- (NSString * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreInt * _Nullable)component3 __attribute__((swift_name("component3()")));
- (NSString * _Nullable)component4 __attribute__((swift_name("component4()")));
- (id<MiamCoreKotlinx_coroutines_coreJob> _Nullable)component5 __attribute__((swift_name("component5()")));
- (MiamCorePointOfSaleState *)doCopyIdSupplier:(MiamCoreInt * _Nullable)idSupplier extIdPointOfSale:(NSString * _Nullable)extIdPointOfSale idPointOfSale:(MiamCoreInt * _Nullable)idPointOfSale origin:(NSString * _Nullable)origin currentJob:(id<MiamCoreKotlinx_coroutines_coreJob> _Nullable)currentJob __attribute__((swift_name("doCopy(idSupplier:extIdPointOfSale:idPointOfSale:origin:currentJob:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) id<MiamCoreKotlinx_coroutines_coreJob> _Nullable currentJob __attribute__((swift_name("currentJob")));
@property (readonly) NSString * _Nullable extIdPointOfSale __attribute__((swift_name("extIdPointOfSale")));
@property (readonly) MiamCoreInt * _Nullable idPointOfSale __attribute__((swift_name("idPointOfSale")));
@property (readonly) MiamCoreInt * _Nullable idSupplier __attribute__((swift_name("idSupplier")));
@property (readonly) NSString * _Nullable origin __attribute__((swift_name("origin")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PointOfSaleStore")))
@interface MiamCorePointOfSaleStore : MiamCoreBase <MiamCoreStore, MiamCoreKoin_coreKoinComponent, MiamCoreKotlinx_coroutines_coreCoroutineScope>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (id<MiamCoreKotlinx_coroutines_coreJob>)dispatchAction:(MiamCorePointOfSaleAction *)action __attribute__((swift_name("dispatch(action:)")));
- (MiamCoreInt * _Nullable)getPosId __attribute__((swift_name("getPosId()")));
- (NSString *)getProviderOrigin __attribute__((swift_name("getProviderOrigin()")));
- (id<MiamCoreKotlinx_coroutines_coreFlow>)observeSideEffect __attribute__((swift_name("observeSideEffect()")));
- (id<MiamCoreKotlinx_coroutines_coreStateFlow>)observeState __attribute__((swift_name("observeState()")));
- (BOOL)samePosExtId:(NSString * _Nullable)extId __attribute__((swift_name("samePos(extId:)")));
- (BOOL)sameSupplierSupplierId:(int32_t)supplierId __attribute__((swift_name("sameSupplier(supplierId:)")));
- (void)setOriginOrigin:(NSString *)origin __attribute__((swift_name("setOrigin(origin:)")));
@property (readonly) id<MiamCoreKotlinCoroutineContext> coroutineContext __attribute__((swift_name("coroutineContext")));
@property (readonly) id<MiamCoreKotlinx_coroutines_coreMutableStateFlow> state __attribute__((swift_name("state")));
@property (readonly) MiamCoreInt * _Nullable supplierId __attribute__((swift_name("supplierId")));
@end;

__attribute__((swift_name("UserAction")))
@interface MiamCoreUserAction : MiamCoreBase <MiamCoreAction>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UserAction.RefreshUser")))
@interface MiamCoreUserActionRefreshUser : MiamCoreUserAction
- (instancetype)initWithIdUser:(NSString * _Nullable)idUser __attribute__((swift_name("init(idUser:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (NSString * _Nullable)component1 __attribute__((swift_name("component1()")));
- (MiamCoreUserActionRefreshUser *)doCopyIdUser:(NSString * _Nullable)idUser __attribute__((swift_name("doCopy(idUser:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable idUser __attribute__((swift_name("idUser")));
@end;

__attribute__((swift_name("UserEffect")))
@interface MiamCoreUserEffect : MiamCoreBase <MiamCoreEffect>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UserState")))
@interface MiamCoreUserState : MiamCoreBase <MiamCoreState>
- (instancetype)initWithUserId:(NSString * _Nullable)userId sessionId:(NSString * _Nullable)sessionId profilingAllowed:(BOOL)profilingAllowed likeIsEnable:(BOOL)likeIsEnable __attribute__((swift_name("init(userId:sessionId:profilingAllowed:likeIsEnable:)"))) __attribute__((objc_designated_initializer));
- (NSString * _Nullable)component1 __attribute__((swift_name("component1()")));
- (NSString * _Nullable)component2 __attribute__((swift_name("component2()")));
- (BOOL)component3 __attribute__((swift_name("component3()")));
- (BOOL)component4 __attribute__((swift_name("component4()")));
- (MiamCoreUserState *)doCopyUserId:(NSString * _Nullable)userId sessionId:(NSString * _Nullable)sessionId profilingAllowed:(BOOL)profilingAllowed likeIsEnable:(BOOL)likeIsEnable __attribute__((swift_name("doCopy(userId:sessionId:profilingAllowed:likeIsEnable:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL likeIsEnable __attribute__((swift_name("likeIsEnable")));
@property (readonly) BOOL profilingAllowed __attribute__((swift_name("profilingAllowed")));
@property (readonly) NSString * _Nullable sessionId __attribute__((swift_name("sessionId")));
@property (readonly) NSString * _Nullable userId __attribute__((swift_name("userId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UserStore")))
@interface MiamCoreUserStore : MiamCoreBase <MiamCoreStore, MiamCoreKoin_coreKoinComponent, MiamCoreKotlinx_coroutines_coreCoroutineScope>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (BOOL)ProfilingForbiden __attribute__((swift_name("ProfilingForbiden()")));
- (id<MiamCoreKotlinx_coroutines_coreJob>)dispatchAction:(MiamCoreUserAction *)action __attribute__((swift_name("dispatch(action:)")));
- (NSString * _Nullable)getSessionId __attribute__((swift_name("getSessionId()")));
- (id<MiamCoreKotlinx_coroutines_coreFlow>)observeSideEffect __attribute__((swift_name("observeSideEffect()")));
- (id<MiamCoreKotlinx_coroutines_coreStateFlow>)observeState __attribute__((swift_name("observeState()")));
- (BOOL)sameSessionSessionId:(NSString *)sessionId __attribute__((swift_name("sameSession(sessionId:)")));
- (BOOL)sameUserUserId:(NSString * _Nullable)userId __attribute__((swift_name("sameUser(userId:)")));
- (void)setEnableLikeIsEnable:(BOOL)isEnable __attribute__((swift_name("setEnableLike(isEnable:)")));
- (void)setProfilingAllowedAllowance:(BOOL)allowance __attribute__((swift_name("setProfilingAllowed(allowance:)")));
- (void)setSessionIdSessionId:(NSString *)sessionId __attribute__((swift_name("setSessionId(sessionId:)")));
@property (readonly) id<MiamCoreKotlinCoroutineContext> coroutineContext __attribute__((swift_name("coroutineContext")));
@property (readonly) id<MiamCoreKotlinx_coroutines_coreMutableStateFlow> state __attribute__((swift_name("state")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ExecutorHelper")))
@interface MiamCoreExecutorHelper : MiamCoreBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreExecutorHelperCompanion *companion __attribute__((swift_name("companion")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ExecutorHelper.Companion")))
@interface MiamCoreExecutorHelperCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreExecutorHelperCompanion *shared __attribute__((swift_name("shared")));
- (void)cancelRunningJobJob:(id<MiamCoreKotlinx_coroutines_coreJob> _Nullable)job __attribute__((swift_name("cancelRunningJob(job:)")));
- (id<MiamCoreKotlinx_coroutines_coreJob>)emptyJob __attribute__((swift_name("emptyJob()")));
@end;

__attribute__((swift_name("MainExecutor")))
@interface MiamCoreMainExecutor : MiamCoreBase <MiamCoreIExecutorScope, MiamCoreKotlinx_coroutines_coreCoroutineScope, MiamCoreKoin_coreKoinComponent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)attach __attribute__((swift_name("attach()")));
- (void)detach __attribute__((swift_name("detach()")));
- (void)launchFlow:(id<MiamCoreKotlinx_coroutines_coreFlow>)flow onSuccess:(void (^)(id _Nullable))onSuccess onError:(void (^)(MiamCoreKotlinThrowable *))onError __attribute__((swift_name("launch(flow:onSuccess:onError:)")));
@property (readonly) id<MiamCoreKotlinCoroutineContext> coroutineContext __attribute__((swift_name("coroutineContext")));
@end;

__attribute__((swift_name("CatalogCategory")))
@interface MiamCoreCatalogCategory : MiamCoreBase
- (instancetype)initWithId:(NSString *)id title:(NSString *)title __attribute__((swift_name("init(id:title:)"))) __attribute__((objc_designated_initializer));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString *title __attribute__((swift_name("title")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ContextHandler")))
@interface MiamCoreContextHandler : MiamCoreBase <MiamCoreKoin_coreKoinComponent, MiamCoreKotlinx_coroutines_coreCoroutineScope>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)emitReadiness __attribute__((swift_name("emitReadiness()")));
- (NSObject * _Nullable)getContextOrNull __attribute__((swift_name("getContextOrNull()")));
- (void)gotAnError __attribute__((swift_name("gotAnError()")));
- (BOOL)isReady __attribute__((swift_name("isReady()")));
- (id<MiamCoreKotlinx_coroutines_coreFlow>)observeReadyEvent __attribute__((swift_name("observeReadyEvent()")));
- (void)onReadyEventCallback:(void (^)(MiamCoreReadyEvent *))callback __attribute__((swift_name("onReadyEvent(callback:)")));
- (void)setContextContext:(NSObject *)context __attribute__((swift_name("setContext(context:)")));
@property (readonly) id<MiamCoreKotlinCoroutineContext> coroutineContext __attribute__((swift_name("coroutineContext")));
@property (readonly) id<MiamCoreKotlinx_coroutines_coreMutableStateFlow> state __attribute__((swift_name("state")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ContextHandlerInstance")))
@interface MiamCoreContextHandlerInstance : MiamCoreBase <MiamCoreKoin_coreKoinComponent>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)contextHandlerInstance __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreContextHandlerInstance *shared __attribute__((swift_name("shared")));
@property (readonly) MiamCoreContextHandler *instance __attribute__((swift_name("instance")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ContextHandlerState")))
@interface MiamCoreContextHandlerState : MiamCoreBase <MiamCoreState>
- (instancetype)initWithIsInError:(BOOL)isInError applicationContext:(NSObject * _Nullable)applicationContext __attribute__((swift_name("init(isInError:applicationContext:)"))) __attribute__((objc_designated_initializer));
- (BOOL)component1 __attribute__((swift_name("component1()")));
- (NSObject * _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreContextHandlerState *)doCopyIsInError:(BOOL)isInError applicationContext:(NSObject * _Nullable)applicationContext __attribute__((swift_name("doCopy(isInError:applicationContext:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSObject * _Nullable applicationContext __attribute__((swift_name("applicationContext")));
@property (readonly) BOOL isInError __attribute__((swift_name("isInError")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GroceriesListHandler")))
@interface MiamCoreGroceriesListHandler : MiamCoreBase <MiamCoreKoin_coreKoinComponent, MiamCoreKotlinx_coroutines_coreCoroutineScope>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)groceriesListHandler __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreGroceriesListHandler *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_coroutines_coreFlow>)getRecipeCountChangeFlow __attribute__((swift_name("getRecipeCountChangeFlow()")));
- (void)onRecipeCountChangeUpdateRecipeCount:(void (^)(MiamCoreInt *))updateRecipeCount __attribute__((swift_name("onRecipeCountChange(updateRecipeCount:)")));
- (id<MiamCoreKotlinx_coroutines_coreJob>)resetGroceriesList __attribute__((swift_name("resetGroceriesList()")));
@property (readonly) id<MiamCoreKotlinCoroutineContext> coroutineContext __attribute__((swift_name("coroutineContext")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("LogHandler")))
@interface MiamCoreLogHandler : MiamCoreBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreLogHandlerCompanion *companion __attribute__((swift_name("companion")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("LogHandler.Companion")))
@interface MiamCoreLogHandlerCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreLogHandlerCompanion *shared __attribute__((swift_name("shared")));
- (void)doInitDebug:(void (^)(NSString *))debug info:(void (^)(NSString *))info warn:(void (^)(NSString *))warn error:(void (^)(NSString *))error __attribute__((swift_name("doInit(debug:info:warn:error:)")));
@property void (^debug)(NSString *) __attribute__((swift_name("debug")));
@property void (^error)(NSString *) __attribute__((swift_name("error")));
@property void (^info)(NSString *) __attribute__((swift_name("info")));
@property void (^warn)(NSString *) __attribute__((swift_name("warn")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PointOfSaleHandler")))
@interface MiamCorePointOfSaleHandler : MiamCoreBase <MiamCoreKoin_coreKoinComponent, MiamCoreKotlinx_coroutines_coreCoroutineScope>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)pointOfSaleHandler __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCorePointOfSaleHandler *shared __attribute__((swift_name("shared")));
- (void)getCatalogCategoriesSetLocalCategories:(void (^)(NSArray<MiamCoreCatalogCategory *> *))setLocalCategories __attribute__((swift_name("getCatalogCategories(setLocalCategories:)")));
- (void)setSupplierSupplierId:(int32_t)supplierId __attribute__((swift_name("setSupplier(supplierId:)")));
- (void)setSupplierOriginOrigin:(NSString *)origin __attribute__((swift_name("setSupplierOrigin(origin:)")));
- (void)updateStoreIdStoreId:(NSString * _Nullable)storeId __attribute__((swift_name("updateStoreId(storeId:)")));
@property (readonly) id<MiamCoreKotlinCoroutineContext> coroutineContext __attribute__((swift_name("coroutineContext")));
@property MiamCoreBoolean *(^isAvailable)(void) __attribute__((swift_name("isAvailable")));
@end;

__attribute__((swift_name("ReadyEvent")))
@interface MiamCoreReadyEvent : MiamCoreBase <MiamCoreEffect>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReadyEvent.isNotReady")))
@interface MiamCoreReadyEventIsNotReady : MiamCoreReadyEvent
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)isNotReady __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreReadyEventIsNotReady *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReadyEvent.isReady")))
@interface MiamCoreReadyEventIsReady : MiamCoreReadyEvent
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)isReady __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreReadyEventIsReady *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ToasterHandler")))
@interface MiamCoreToasterHandler : MiamCoreBase <MiamCoreKoin_coreKoinComponent>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)toasterHandler __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreToasterHandler *shared __attribute__((swift_name("shared")));
- (void)onAddRecipe __attribute__((swift_name("onAddRecipe()")));
- (void)onLikeRecipe __attribute__((swift_name("onLikeRecipe()")));
- (void)onSuccessMessage:(NSString *)message __attribute__((swift_name("onSuccess(message:)")));
- (void)setOnAddRecipeTextMessage:(NSString *)message __attribute__((swift_name("setOnAddRecipeText(message:)")));
- (void)setOnLikeRecipeTextMessage:(NSString *)message __attribute__((swift_name("setOnLikeRecipeText(message:)")));
- (void)setOnSuccessSuccessToasterCallBack:(void (^)(NSString *))successToasterCallBack __attribute__((swift_name("setOnSuccess(successToasterCallBack:)")));
@property (readonly) id<MiamCoreKotlinx_coroutines_coreMutableStateFlow> state __attribute__((swift_name("state")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ToasterState")))
@interface MiamCoreToasterState : MiamCoreBase <MiamCoreState>
- (instancetype)initWithOnSuccess:(void (^)(NSString *))onSuccess onAddRecipeText:(NSString *)onAddRecipeText onLikeRecipeText:(NSString *)onLikeRecipeText __attribute__((swift_name("init(onSuccess:onAddRecipeText:onLikeRecipeText:)"))) __attribute__((objc_designated_initializer));
- (void (^)(NSString *))component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (MiamCoreToasterState *)doCopyOnSuccess:(void (^)(NSString *))onSuccess onAddRecipeText:(NSString *)onAddRecipeText onLikeRecipeText:(NSString *)onLikeRecipeText __attribute__((swift_name("doCopy(onSuccess:onAddRecipeText:onLikeRecipeText:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *onAddRecipeText __attribute__((swift_name("onAddRecipeText")));
@property (readonly) NSString *onLikeRecipeText __attribute__((swift_name("onLikeRecipeText")));
@property (readonly) void (^onSuccess)(NSString *) __attribute__((swift_name("onSuccess")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UserHandler")))
@interface MiamCoreUserHandler : MiamCoreBase <MiamCoreKoin_coreKoinComponent>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)userHandler __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreUserHandler *shared __attribute__((swift_name("shared")));
- (void)setEnableLikeIsEnable:(BOOL)isEnable __attribute__((swift_name("setEnableLike(isEnable:)")));
- (void)setProfilingAllowedAllowance:(BOOL)allowance __attribute__((swift_name("setProfilingAllowed(allowance:)")));
- (void)updateUserIdUserId:(NSString * _Nullable)userId __attribute__((swift_name("updateUserId(userId:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketComparator")))
@interface MiamCoreBasketComparator : MiamCoreBase
- (instancetype)initWithExtIdToComparisonItem:(NSDictionary<NSString *, MiamCoreBasketComparisonItem *> *)extIdToComparisonItem __attribute__((swift_name("init(extIdToComparisonItem:)"))) __attribute__((objc_designated_initializer));
- (NSDictionary<NSString *, MiamCoreBasketComparisonItem *> *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreBasketComparator *)doCopyExtIdToComparisonItem:(NSDictionary<NSString *, MiamCoreBasketComparisonItem *> *)extIdToComparisonItem __attribute__((swift_name("doCopy(extIdToComparisonItem:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (MiamCoreBasketComparator *)doInitRetailerBasket:(NSArray<MiamCoreRetailerProduct *> *)retailerBasket miamActiveBasket:(NSArray<MiamCoreBasketEntry *> *)miamActiveBasket __attribute__((swift_name("doInit(retailerBasket:miamActiveBasket:)")));
- (NSMutableArray<MiamCoreRetailerProduct *> *)resolveFromMiamNewBasketComparator:(MiamCoreBasketComparator *)newBasketComparator __attribute__((swift_name("resolveFromMiam(newBasketComparator:)")));
- (NSArray<MiamCoreAlterQuantityBasketEntry *> *)resolveFromRetailerNewComparator:(MiamCoreBasketComparator *)newComparator __attribute__((swift_name("resolveFromRetailer(newComparator:)")));
- (NSString *)description __attribute__((swift_name("description()")));
- (MiamCoreBasketComparator *)updateReceivedFromMiamMiamActiveBasket:(NSArray<MiamCoreBasketEntry *> *)miamActiveBasket __attribute__((swift_name("updateReceivedFromMiam(miamActiveBasket:)")));
- (MiamCoreBasketComparator *)updateReceivedFromRetailerRetailerBasket:(NSArray<MiamCoreRetailerProduct *> *)retailerBasket __attribute__((swift_name("updateReceivedFromRetailer(retailerBasket:)")));
@property (readonly) NSDictionary<NSString *, MiamCoreBasketComparisonItem *> *extIdToComparisonItem __attribute__((swift_name("extIdToComparisonItem")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketComparatorData")))
@interface MiamCoreBasketComparatorData : MiamCoreBase
- (instancetype)initWithExtIdToComparisonItem:(MiamCoreMutableDictionary<NSString *, MiamCoreBasketComparisonItem *> *)extIdToComparisonItem __attribute__((swift_name("init(extIdToComparisonItem:)"))) __attribute__((objc_designated_initializer));
- (MiamCoreMutableDictionary<NSString *, MiamCoreBasketComparisonItem *> *)component1 __attribute__((swift_name("component1()")));
- (MiamCoreBasketComparatorData *)doCopyExtIdToComparisonItem:(MiamCoreMutableDictionary<NSString *, MiamCoreBasketComparisonItem *> *)extIdToComparisonItem __attribute__((swift_name("doCopy(extIdToComparisonItem:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (void)doInitRetailerBasket:(NSArray<MiamCoreRetailerProduct *> *)retailerBasket miamActiveBasket:(NSArray<MiamCoreBasketEntry *> *)miamActiveBasket __attribute__((swift_name("doInit(retailerBasket:miamActiveBasket:)")));
- (NSString *)description __attribute__((swift_name("description()")));
- (void)updateMapFromMiamMiamActiveBasket:(NSArray<MiamCoreBasketEntry *> *)miamActiveBasket __attribute__((swift_name("updateMapFromMiam(miamActiveBasket:)")));
- (void)updateMapFromRetailerRetailerBasket:(NSArray<MiamCoreRetailerProduct *> *)retailerBasket __attribute__((swift_name("updateMapFromRetailer(retailerBasket:)")));
@property (readonly) MiamCoreMutableDictionary<NSString *, MiamCoreBasketComparisonItem *> *extIdToComparisonItem __attribute__((swift_name("extIdToComparisonItem")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketComparisonItem")))
@interface MiamCoreBasketComparisonItem : MiamCoreBase
- (instancetype)initWithRetailerId:(NSString *)retailerId miamBasketEntryIds:(NSDictionary<NSString *, MiamCoreInt *> *)miamBasketEntryIds retailerProduct:(MiamCoreRetailerProduct *)retailerProduct __attribute__((swift_name("init(retailerId:miamBasketEntryIds:retailerProduct:)"))) __attribute__((objc_designated_initializer));
- (MiamCoreBasketComparisonItem *)addMiamEntryBasketEntry:(MiamCoreBasketEntry *)basketEntry __attribute__((swift_name("addMiamEntry(basketEntry:)")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSDictionary<NSString *, MiamCoreInt *> *)component2 __attribute__((swift_name("component2()")));
- (MiamCoreRetailerProduct *)component3 __attribute__((swift_name("component3()")));
- (MiamCoreBasketComparisonItem *)doCopyRetailerId:(NSString *)retailerId miamBasketEntryIds:(NSDictionary<NSString *, MiamCoreInt *> *)miamBasketEntryIds retailerProduct:(MiamCoreRetailerProduct *)retailerProduct __attribute__((swift_name("doCopy(retailerId:miamBasketEntryIds:retailerProduct:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSArray<MiamCoreAlterQuantityBasketEntry *> *)miamProductRemoved __attribute__((swift_name("miamProductRemoved()")));
- (MiamCoreRetailerProduct * _Nullable)retailerProductAddedOrUpdatedFromMiamPreviousCompItem:(MiamCoreBasketComparisonItem * _Nullable)previousCompItem __attribute__((swift_name("retailerProductAddedOrUpdatedFromMiam(previousCompItem:)")));
- (MiamCoreRetailerProduct * _Nullable)retailerProductsRemovedFromMiamNewCompItem:(MiamCoreBasketComparisonItem * _Nullable)newCompItem __attribute__((swift_name("retailerProductsRemovedFromMiam(newCompItem:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *firstBasketEntryId __attribute__((swift_name("firstBasketEntryId")));
@property (readonly) NSDictionary<NSString *, MiamCoreInt *> *miamBasketEntryIds __attribute__((swift_name("miamBasketEntryIds")));
@property (readonly) int32_t miamQuantity __attribute__((swift_name("miamQuantity")));
@property (readonly) NSString *retailerId __attribute__((swift_name("retailerId")));
@property (readonly) MiamCoreRetailerProduct *retailerProduct __attribute__((swift_name("retailerProduct")));
@property (readonly) int32_t retailerQuantity __attribute__((swift_name("retailerQuantity")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketHandler")))
@interface MiamCoreBasketHandler : MiamCoreBase <MiamCoreKoin_coreKoinComponent, MiamCoreKotlinx_coroutines_coreCoroutineScope>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)basketChangeMiamActiveBasket:(NSArray<MiamCoreBasketEntry *> *)miamActiveBasket __attribute__((swift_name("basketChange(miamActiveBasket:)")));
- (void)dispose __attribute__((swift_name("dispose()")));
- (void)handlePaymentTotalAmount:(double)totalAmount __attribute__((swift_name("handlePayment(totalAmount:)")));
- (BOOL)isReady __attribute__((swift_name("isReady()")));
- (void)pushProductsToMiamBasketRetailerBasket:(NSArray<MiamCoreRetailerProduct *> *)retailerBasket __attribute__((swift_name("pushProductsToMiamBasket(retailerBasket:)")));
- (void)setListenToRetailerBasketFunc:(void (^)(void))func __attribute__((swift_name("setListenToRetailerBasket(func:)")));
- (void)setPushProductsToRetailerBasketFunc:(void (^)(NSArray<MiamCoreRetailerProduct *> *))func __attribute__((swift_name("setPushProductsToRetailerBasket(func:)")));
@property (readonly) id<MiamCoreKotlinCoroutineContext> coroutineContext __attribute__((swift_name("coroutineContext")));
@property (readonly) id<MiamCoreKotlinx_coroutines_coreMutableStateFlow> state __attribute__((swift_name("state")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketHandlerInstance")))
@interface MiamCoreBasketHandlerInstance : MiamCoreBase <MiamCoreKoin_coreKoinComponent>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)basketHandlerInstance __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreBasketHandlerInstance *shared __attribute__((swift_name("shared")));
@property (readonly) MiamCoreBasketHandler *instance __attribute__((swift_name("instance")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasketHandlerState")))
@interface MiamCoreBasketHandlerState : MiamCoreBase <MiamCoreState>
- (instancetype)initWithComparator:(MiamCoreBasketComparator * _Nullable)comparator isProcessingRetailerEvent:(BOOL)isProcessingRetailerEvent firstMiamActiveBasket:(NSArray<MiamCoreBasketEntry *> * _Nullable)firstMiamActiveBasket firstRetailerBasket:(NSArray<MiamCoreRetailerProduct *> * _Nullable)firstRetailerBasket pushProductsToRetailerBasket:(void (^)(NSArray<MiamCoreRetailerProduct *> *))pushProductsToRetailerBasket listenToRetailerBasket:(void (^)(void))listenToRetailerBasket __attribute__((swift_name("init(comparator:isProcessingRetailerEvent:firstMiamActiveBasket:firstRetailerBasket:pushProductsToRetailerBasket:listenToRetailerBasket:)"))) __attribute__((objc_designated_initializer));
- (MiamCoreBasketComparator * _Nullable)component1 __attribute__((swift_name("component1()")));
- (BOOL)component2 __attribute__((swift_name("component2()")));
- (NSArray<MiamCoreBasketEntry *> * _Nullable)component3 __attribute__((swift_name("component3()")));
- (NSArray<MiamCoreRetailerProduct *> * _Nullable)component4 __attribute__((swift_name("component4()")));
- (void (^)(NSArray<MiamCoreRetailerProduct *> *))component5 __attribute__((swift_name("component5()")));
- (void (^)(void))component6 __attribute__((swift_name("component6()")));
- (MiamCoreBasketHandlerState *)doCopyComparator:(MiamCoreBasketComparator * _Nullable)comparator isProcessingRetailerEvent:(BOOL)isProcessingRetailerEvent firstMiamActiveBasket:(NSArray<MiamCoreBasketEntry *> * _Nullable)firstMiamActiveBasket firstRetailerBasket:(NSArray<MiamCoreRetailerProduct *> * _Nullable)firstRetailerBasket pushProductsToRetailerBasket:(void (^)(NSArray<MiamCoreRetailerProduct *> *))pushProductsToRetailerBasket listenToRetailerBasket:(void (^)(void))listenToRetailerBasket __attribute__((swift_name("doCopy(comparator:isProcessingRetailerEvent:firstMiamActiveBasket:firstRetailerBasket:pushProductsToRetailerBasket:listenToRetailerBasket:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreBasketComparator * _Nullable comparator __attribute__((swift_name("comparator")));
@property (readonly) NSArray<MiamCoreBasketEntry *> * _Nullable firstMiamActiveBasket __attribute__((swift_name("firstMiamActiveBasket")));
@property (readonly) NSArray<MiamCoreRetailerProduct *> * _Nullable firstRetailerBasket __attribute__((swift_name("firstRetailerBasket")));
@property (readonly) BOOL isProcessingRetailerEvent __attribute__((swift_name("isProcessingRetailerEvent")));
@property (readonly) void (^listenToRetailerBasket)(void) __attribute__((swift_name("listenToRetailerBasket")));
@property (readonly) void (^pushProductsToRetailerBasket)(NSArray<MiamCoreRetailerProduct *> *) __attribute__((swift_name("pushProductsToRetailerBasket")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KoinKt")))
@interface MiamCoreKoinKt : MiamCoreBase
+ (MiamCoreKoin_coreKoinApplication *)doInitKoin __attribute__((swift_name("doInitKoin()")));
+ (MiamCoreKoin_coreKoinApplication *)doInitKoinAppDeclaration:(void (^)(MiamCoreKoin_coreKoinApplication *))appDeclaration __attribute__((swift_name("doInitKoin(appDeclaration:)")));
@property (class, readonly) MiamCoreKoin_coreModule *dispatcherModule __attribute__((swift_name("dispatcherModule")));
@property (class, readonly) MiamCoreKoin_coreModule *repositoryModule __attribute__((swift_name("repositoryModule")));
@property (class, readonly) MiamCoreKoin_coreModule *servicesModule __attribute__((swift_name("servicesModule")));
@property (class, readonly) MiamCoreKoin_coreModule *storeModule __attribute__((swift_name("storeModule")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ModuleKt")))
@interface MiamCoreModuleKt : MiamCoreBase
+ (MiamCoreKoin_coreModule *)platformModule __attribute__((swift_name("platformModule()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("WrappingKt")))
@interface MiamCoreWrappingKt : MiamCoreBase
+ (id _Nullable)dualLetP1:(id _Nullable)p1 p2:(id _Nullable)p2 block:(id _Nullable (^)(id, id))block __attribute__((swift_name("dualLet(p1:p2:block:)")));
+ (id _Nullable)letElseP:(id _Nullable)p block:(id _Nullable (^)(id _Nullable))block elseBlock:(id _Nullable (^)(void))elseBlock __attribute__((swift_name("letElse(p:block:elseBlock:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RecordKt")))
@interface MiamCoreRecordKt : MiamCoreBase
+ (int32_t)getIntValueOrThrow:(NSDictionary<NSString *, MiamCoreKotlinx_serialization_jsonJsonElement *> *)receiver attribute:(NSString *)attribute __attribute__((swift_name("getIntValueOrThrow(_:attribute:)")));
+ (NSString * _Nullable)getStringValueOrNull:(NSDictionary<NSString *, MiamCoreKotlinx_serialization_jsonJsonElement *> *)receiver attribute:(NSString *)attribute __attribute__((swift_name("getStringValueOrNull(_:attribute:)")));
+ (NSString *)getStringValueOrThrow:(NSDictionary<NSString *, MiamCoreKotlinx_serialization_jsonJsonElement *> *)receiver attribute:(NSString *)attribute __attribute__((swift_name("getStringValueOrThrow(_:attribute:)")));
@property (class, readonly) MiamCoreKotlinx_serialization_jsonJson *jsonFormat __attribute__((swift_name("jsonFormat")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("LocalSharedPreferencesRepositoryKt")))
@interface MiamCoreLocalSharedPreferencesRepositoryKt : MiamCoreBase
+ (NSArray<NSString *> * _Nullable)getArrayOrNull:(NSObject *)receiver key:(NSString *)key __attribute__((swift_name("getArrayOrNull(_:key:)")));
+ (MiamCoreInt * _Nullable)getIntOrNull:(NSObject *)receiver key:(NSString *)key __attribute__((swift_name("getIntOrNull(_:key:)")));
+ (void)putArray:(NSObject *)receiver key:(NSString *)key value:(NSArray<NSString *> *)value __attribute__((swift_name("putArray(_:key:value:)")));
+ (void)putInt:(NSObject *)receiver key:(NSString *)key value:(int32_t)value __attribute__((swift_name("putInt(_:key:value:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreKoin")))
@interface MiamCoreKoin_coreKoin : MiamCoreBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)close __attribute__((swift_name("close()")));
- (void)createEagerInstances __attribute__((swift_name("createEagerInstances()")));
- (MiamCoreKoin_coreScope *)createScopeT:(id<MiamCoreKoin_coreKoinScopeComponent>)t __attribute__((swift_name("createScope(t:)")));
- (MiamCoreKoin_coreScope *)createScopeScopeId:(NSString *)scopeId __attribute__((swift_name("createScope(scopeId:)")));
- (MiamCoreKoin_coreScope *)createScopeScopeId:(NSString *)scopeId source:(id _Nullable)source __attribute__((swift_name("createScope(scopeId:source:)")));
- (MiamCoreKoin_coreScope *)createScopeScopeId:(NSString *)scopeId qualifier:(id<MiamCoreKoin_coreQualifier>)qualifier source:(id _Nullable)source __attribute__((swift_name("createScope(scopeId:qualifier:source:)")));
- (void)declareInstance:(id _Nullable)instance qualifier:(id<MiamCoreKoin_coreQualifier> _Nullable)qualifier secondaryTypes:(NSArray<id<MiamCoreKotlinKClass>> *)secondaryTypes allowOverride:(BOOL)allowOverride __attribute__((swift_name("declare(instance:qualifier:secondaryTypes:allowOverride:)")));
- (void)deletePropertyKey:(NSString *)key __attribute__((swift_name("deleteProperty(key:)")));
- (void)deleteScopeScopeId:(NSString *)scopeId __attribute__((swift_name("deleteScope(scopeId:)")));
- (id _Nullable)getClazz:(id<MiamCoreKotlinKClass>)clazz qualifier:(id<MiamCoreKoin_coreQualifier> _Nullable)qualifier parameters:(MiamCoreKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("get(clazz:qualifier:parameters:)")));
- (id)getQualifier:(id<MiamCoreKoin_coreQualifier> _Nullable)qualifier parameters:(MiamCoreKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("get(qualifier:parameters:)")));
- (NSArray<id> *)getAll __attribute__((swift_name("getAll()")));
- (MiamCoreKoin_coreScope *)getOrCreateScopeScopeId:(NSString *)scopeId __attribute__((swift_name("getOrCreateScope(scopeId:)")));
- (MiamCoreKoin_coreScope *)getOrCreateScopeScopeId:(NSString *)scopeId qualifier:(id<MiamCoreKoin_coreQualifier>)qualifier source:(id _Nullable)source __attribute__((swift_name("getOrCreateScope(scopeId:qualifier:source:)")));
- (id _Nullable)getOrNullClazz:(id<MiamCoreKotlinKClass>)clazz qualifier:(id<MiamCoreKoin_coreQualifier> _Nullable)qualifier parameters:(MiamCoreKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("getOrNull(clazz:qualifier:parameters:)")));
- (id _Nullable)getOrNullQualifier:(id<MiamCoreKoin_coreQualifier> _Nullable)qualifier parameters:(MiamCoreKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("getOrNull(qualifier:parameters:)")));
- (id _Nullable)getPropertyKey:(NSString *)key __attribute__((swift_name("getProperty(key:)")));
- (id)getPropertyKey:(NSString *)key defaultValue:(id)defaultValue __attribute__((swift_name("getProperty(key:defaultValue:)")));
- (MiamCoreKoin_coreScope *)getScopeScopeId:(NSString *)scopeId __attribute__((swift_name("getScope(scopeId:)")));
- (MiamCoreKoin_coreScope * _Nullable)getScopeOrNullScopeId:(NSString *)scopeId __attribute__((swift_name("getScopeOrNull(scopeId:)")));
- (id<MiamCoreKotlinLazy>)injectQualifier:(id<MiamCoreKoin_coreQualifier> _Nullable)qualifier mode:(MiamCoreKotlinLazyThreadSafetyMode *)mode parameters:(MiamCoreKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("inject(qualifier:mode:parameters:)")));
- (id<MiamCoreKotlinLazy>)injectOrNullQualifier:(id<MiamCoreKoin_coreQualifier> _Nullable)qualifier mode:(MiamCoreKotlinLazyThreadSafetyMode *)mode parameters:(MiamCoreKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("injectOrNull(qualifier:mode:parameters:)")));
- (void)loadModulesModules:(NSArray<MiamCoreKoin_coreModule *> *)modules allowOverride:(BOOL)allowOverride __attribute__((swift_name("loadModules(modules:allowOverride:)")));
- (void)setPropertyKey:(NSString *)key value:(id)value __attribute__((swift_name("setProperty(key:value:)")));
- (void)setupLoggerLogger:(MiamCoreKoin_coreLogger *)logger __attribute__((swift_name("setupLogger(logger:)")));
- (void)unloadModulesModules:(NSArray<MiamCoreKoin_coreModule *> *)modules __attribute__((swift_name("unloadModules(modules:)")));
@property (readonly) MiamCoreKoin_coreInstanceRegistry *instanceRegistry __attribute__((swift_name("instanceRegistry")));
@property (readonly) MiamCoreKoin_coreLogger *logger __attribute__((swift_name("logger")));
@property (readonly) MiamCoreKoin_corePropertyRegistry *propertyRegistry __attribute__((swift_name("propertyRegistry")));
@property (readonly) MiamCoreKoin_coreScopeRegistry *scopeRegistry __attribute__((swift_name("scopeRegistry")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreFlow")))
@protocol MiamCoreKotlinx_coroutines_coreFlow
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)collectCollector:(id<MiamCoreKotlinx_coroutines_coreFlowCollector>)collector completionHandler:(void (^)(MiamCoreKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("collect(collector:completionHandler:)")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreSharedFlow")))
@protocol MiamCoreKotlinx_coroutines_coreSharedFlow <MiamCoreKotlinx_coroutines_coreFlow>
@required
@property (readonly) NSArray<id> *replayCache __attribute__((swift_name("replayCache")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreStateFlow")))
@protocol MiamCoreKotlinx_coroutines_coreStateFlow <MiamCoreKotlinx_coroutines_coreSharedFlow>
@required
@property (readonly) id _Nullable value __attribute__((swift_name("value")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreFlowCollector")))
@protocol MiamCoreKotlinx_coroutines_coreFlowCollector
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)emitValue:(id _Nullable)value completionHandler:(void (^)(MiamCoreKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("emit(value:completionHandler:)")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreMutableSharedFlow")))
@protocol MiamCoreKotlinx_coroutines_coreMutableSharedFlow <MiamCoreKotlinx_coroutines_coreSharedFlow, MiamCoreKotlinx_coroutines_coreFlowCollector>
@required
- (void)resetReplayCache __attribute__((swift_name("resetReplayCache()")));
- (BOOL)tryEmitValue:(id _Nullable)value __attribute__((swift_name("tryEmit(value:)")));
@property (readonly) id<MiamCoreKotlinx_coroutines_coreStateFlow> subscriptionCount __attribute__((swift_name("subscriptionCount")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreMutableStateFlow")))
@protocol MiamCoreKotlinx_coroutines_coreMutableStateFlow <MiamCoreKotlinx_coroutines_coreStateFlow, MiamCoreKotlinx_coroutines_coreMutableSharedFlow>
@required
- (BOOL)compareAndSetExpect:(id _Nullable)expect update:(id _Nullable)update __attribute__((swift_name("compareAndSet(expect:update:)")));
- (void)setValue:(id _Nullable)value __attribute__((swift_name("setValue(_:)")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreJob")))
@protocol MiamCoreKotlinx_coroutines_coreJob <MiamCoreKotlinCoroutineContextElement>
@required
- (id<MiamCoreKotlinx_coroutines_coreChildHandle>)attachChildChild:(id<MiamCoreKotlinx_coroutines_coreChildJob>)child __attribute__((swift_name("attachChild(child:)")));
- (void)cancelCause:(MiamCoreKotlinCancellationException * _Nullable)cause __attribute__((swift_name("cancel(cause:)")));
- (MiamCoreKotlinCancellationException *)getCancellationException __attribute__((swift_name("getCancellationException()")));
- (id<MiamCoreKotlinx_coroutines_coreDisposableHandle>)invokeOnCompletionOnCancelling:(BOOL)onCancelling invokeImmediately:(BOOL)invokeImmediately handler:(void (^)(MiamCoreKotlinThrowable * _Nullable))handler __attribute__((swift_name("invokeOnCompletion(onCancelling:invokeImmediately:handler:)")));
- (id<MiamCoreKotlinx_coroutines_coreDisposableHandle>)invokeOnCompletionHandler:(void (^)(MiamCoreKotlinThrowable * _Nullable))handler __attribute__((swift_name("invokeOnCompletion(handler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)joinWithCompletionHandler:(void (^)(MiamCoreKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("join(completionHandler:)")));
- (id<MiamCoreKotlinx_coroutines_coreJob>)plusOther_:(id<MiamCoreKotlinx_coroutines_coreJob>)other __attribute__((swift_name("plus(other_:)"))) __attribute__((unavailable("Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")));
- (BOOL)start __attribute__((swift_name("start()")));
@property (readonly) id<MiamCoreKotlinSequence> children __attribute__((swift_name("children")));
@property (readonly) BOOL isActive __attribute__((swift_name("isActive")));
@property (readonly) BOOL isCancelled __attribute__((swift_name("isCancelled")));
@property (readonly) BOOL isCompleted __attribute__((swift_name("isCompleted")));
@property (readonly) id<MiamCoreKotlinx_coroutines_coreSelectClause0> onJoin __attribute__((swift_name("onJoin")));
@end;

__attribute__((swift_name("KotlinCoroutineContextKey")))
@protocol MiamCoreKotlinCoroutineContextKey
@required
@end;

__attribute__((swift_name("KotlinContinuation")))
@protocol MiamCoreKotlinContinuation
@required
- (void)resumeWithResult:(id _Nullable)result __attribute__((swift_name("resumeWith(result:)")));
@property (readonly) id<MiamCoreKotlinCoroutineContext> context __attribute__((swift_name("context")));
@end;

__attribute__((swift_name("KotlinAbstractCoroutineContextKey")))
@interface MiamCoreKotlinAbstractCoroutineContextKey<B, E> : MiamCoreBase <MiamCoreKotlinCoroutineContextKey>
- (instancetype)initWithBaseKey:(id<MiamCoreKotlinCoroutineContextKey>)baseKey safeCast:(E _Nullable (^)(id<MiamCoreKotlinCoroutineContextElement>))safeCast __attribute__((swift_name("init(baseKey:safeCast:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_coroutines_coreCoroutineDispatcher.Key")))
@interface MiamCoreKotlinx_coroutines_coreCoroutineDispatcherKey : MiamCoreKotlinAbstractCoroutineContextKey<id<MiamCoreKotlinContinuationInterceptor>, MiamCoreKotlinx_coroutines_coreCoroutineDispatcher *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithBaseKey:(id<MiamCoreKotlinCoroutineContextKey>)baseKey safeCast:(id<MiamCoreKotlinCoroutineContextElement> _Nullable (^)(id<MiamCoreKotlinCoroutineContextElement>))safeCast __attribute__((swift_name("init(baseKey:safeCast:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)key __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreKotlinx_coroutines_coreCoroutineDispatcherKey *shared __attribute__((swift_name("shared")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreRunnable")))
@protocol MiamCoreKotlinx_coroutines_coreRunnable
@required
- (void)run __attribute__((swift_name("run()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinArray")))
@interface MiamCoreKotlinArray<T> : MiamCoreBase
+ (instancetype)arrayWithSize:(int32_t)size init:(T _Nullable (^)(MiamCoreInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (T _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id<MiamCoreKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(T _Nullable)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end;

__attribute__((swift_name("Kotlinx_serialization_jsonJsonElement")))
@interface MiamCoreKotlinx_serialization_jsonJsonElement : MiamCoreBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) MiamCoreKotlinx_serialization_jsonJsonElementCompanion *companion __attribute__((swift_name("companion")));
@end;

__attribute__((swift_name("KotlinKDeclarationContainer")))
@protocol MiamCoreKotlinKDeclarationContainer
@required
@end;

__attribute__((swift_name("KotlinKAnnotatedElement")))
@protocol MiamCoreKotlinKAnnotatedElement
@required
@end;

__attribute__((swift_name("KotlinKClassifier")))
@protocol MiamCoreKotlinKClassifier
@required
@end;

__attribute__((swift_name("KotlinKClass")))
@protocol MiamCoreKotlinKClass <MiamCoreKotlinKDeclarationContainer, MiamCoreKotlinKAnnotatedElement, MiamCoreKotlinKClassifier>
@required
- (BOOL)isInstanceValue:(id _Nullable)value __attribute__((swift_name("isInstance(value:)")));
@property (readonly) NSString * _Nullable qualifiedName __attribute__((swift_name("qualifiedName")));
@property (readonly) NSString * _Nullable simpleName __attribute__((swift_name("simpleName")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreEncoder")))
@protocol MiamCoreKotlinx_serialization_coreEncoder
@required
- (id<MiamCoreKotlinx_serialization_coreCompositeEncoder>)beginCollectionDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor collectionSize:(int32_t)collectionSize __attribute__((swift_name("beginCollection(descriptor:collectionSize:)")));
- (id<MiamCoreKotlinx_serialization_coreCompositeEncoder>)beginStructureDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("beginStructure(descriptor:)")));
- (void)encodeBooleanValue:(BOOL)value __attribute__((swift_name("encodeBoolean(value:)")));
- (void)encodeByteValue:(int8_t)value __attribute__((swift_name("encodeByte(value:)")));
- (void)encodeCharValue:(unichar)value __attribute__((swift_name("encodeChar(value:)")));
- (void)encodeDoubleValue:(double)value __attribute__((swift_name("encodeDouble(value:)")));
- (void)encodeEnumEnumDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)enumDescriptor index:(int32_t)index __attribute__((swift_name("encodeEnum(enumDescriptor:index:)")));
- (void)encodeFloatValue:(float)value __attribute__((swift_name("encodeFloat(value:)")));
- (id<MiamCoreKotlinx_serialization_coreEncoder>)encodeInlineInlineDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)inlineDescriptor __attribute__((swift_name("encodeInline(inlineDescriptor:)")));
- (void)encodeIntValue:(int32_t)value __attribute__((swift_name("encodeInt(value:)")));
- (void)encodeLongValue:(int64_t)value __attribute__((swift_name("encodeLong(value:)")));
- (void)encodeNotNullMark __attribute__((swift_name("encodeNotNullMark()")));
- (void)encodeNull __attribute__((swift_name("encodeNull()")));
- (void)encodeNullableSerializableValueSerializer:(id<MiamCoreKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableValue(serializer:value:)")));
- (void)encodeSerializableValueSerializer:(id<MiamCoreKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableValue(serializer:value:)")));
- (void)encodeShortValue:(int16_t)value __attribute__((swift_name("encodeShort(value:)")));
- (void)encodeStringValue:(NSString *)value __attribute__((swift_name("encodeString(value:)")));
@property (readonly) MiamCoreKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreSerialDescriptor")))
@protocol MiamCoreKotlinx_serialization_coreSerialDescriptor
@required
- (NSArray<id<MiamCoreKotlinAnnotation>> *)getElementAnnotationsIndex:(int32_t)index __attribute__((swift_name("getElementAnnotations(index:)")));
- (id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)getElementDescriptorIndex:(int32_t)index __attribute__((swift_name("getElementDescriptor(index:)")));
- (int32_t)getElementIndexName:(NSString *)name __attribute__((swift_name("getElementIndex(name:)")));
- (NSString *)getElementNameIndex:(int32_t)index __attribute__((swift_name("getElementName(index:)")));
- (BOOL)isElementOptionalIndex:(int32_t)index __attribute__((swift_name("isElementOptional(index:)")));
@property (readonly) NSArray<id<MiamCoreKotlinAnnotation>> *annotations __attribute__((swift_name("annotations")));
@property (readonly) int32_t elementsCount __attribute__((swift_name("elementsCount")));
@property (readonly) BOOL isInline __attribute__((swift_name("isInline")));
@property (readonly) BOOL isNullable __attribute__((swift_name("isNullable")));
@property (readonly) MiamCoreKotlinx_serialization_coreSerialKind *kind __attribute__((swift_name("kind")));
@property (readonly) NSString *serialName __attribute__((swift_name("serialName")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreDecoder")))
@protocol MiamCoreKotlinx_serialization_coreDecoder
@required
- (id<MiamCoreKotlinx_serialization_coreCompositeDecoder>)beginStructureDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("beginStructure(descriptor:)")));
- (BOOL)decodeBoolean __attribute__((swift_name("decodeBoolean()")));
- (int8_t)decodeByte __attribute__((swift_name("decodeByte()")));
- (unichar)decodeChar __attribute__((swift_name("decodeChar()")));
- (double)decodeDouble __attribute__((swift_name("decodeDouble()")));
- (int32_t)decodeEnumEnumDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)enumDescriptor __attribute__((swift_name("decodeEnum(enumDescriptor:)")));
- (float)decodeFloat __attribute__((swift_name("decodeFloat()")));
- (id<MiamCoreKotlinx_serialization_coreDecoder>)decodeInlineInlineDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)inlineDescriptor __attribute__((swift_name("decodeInline(inlineDescriptor:)")));
- (int32_t)decodeInt __attribute__((swift_name("decodeInt()")));
- (int64_t)decodeLong __attribute__((swift_name("decodeLong()")));
- (BOOL)decodeNotNullMark __attribute__((swift_name("decodeNotNullMark()")));
- (MiamCoreKotlinNothing * _Nullable)decodeNull __attribute__((swift_name("decodeNull()")));
- (id _Nullable)decodeNullableSerializableValueDeserializer:(id<MiamCoreKotlinx_serialization_coreDeserializationStrategy>)deserializer __attribute__((swift_name("decodeNullableSerializableValue(deserializer:)")));
- (id _Nullable)decodeSerializableValueDeserializer:(id<MiamCoreKotlinx_serialization_coreDeserializationStrategy>)deserializer __attribute__((swift_name("decodeSerializableValue(deserializer:)")));
- (int16_t)decodeShort __attribute__((swift_name("decodeShort()")));
- (NSString *)decodeString __attribute__((swift_name("decodeString()")));
@property (readonly) MiamCoreKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinEnumCompanion")))
@interface MiamCoreKotlinEnumCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreKotlinEnumCompanion *shared __attribute__((swift_name("shared")));
@end;

__attribute__((swift_name("KotlinThrowable")))
@interface MiamCoreKotlinThrowable : MiamCoreBase
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(MiamCoreKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(MiamCoreKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (MiamCoreKotlinArray<NSString *> *)getStackTrace __attribute__((swift_name("getStackTrace()")));
- (void)printStackTrace __attribute__((swift_name("printStackTrace()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreKotlinThrowable * _Nullable cause __attribute__((swift_name("cause")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
- (NSError *)asError __attribute__((swift_name("asError()")));
@end;

__attribute__((swift_name("KotlinException")))
@interface MiamCoreKotlinException : MiamCoreKotlinThrowable
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(MiamCoreKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(MiamCoreKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("KotlinRuntimeException")))
@interface MiamCoreKotlinRuntimeException : MiamCoreKotlinException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(MiamCoreKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(MiamCoreKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("KotlinIllegalStateException")))
@interface MiamCoreKotlinIllegalStateException : MiamCoreKotlinRuntimeException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(MiamCoreKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(MiamCoreKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("KotlinCancellationException")))
@interface MiamCoreKotlinCancellationException : MiamCoreKotlinIllegalStateException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(MiamCoreKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(MiamCoreKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinUnit")))
@interface MiamCoreKotlinUnit : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)unit __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreKotlinUnit *shared __attribute__((swift_name("shared")));
- (NSString *)description __attribute__((swift_name("description()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinNothing")))
@interface MiamCoreKotlinNothing : MiamCoreBase
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreKoinApplication")))
@interface MiamCoreKoin_coreKoinApplication : MiamCoreBase
@property (class, readonly, getter=companion) MiamCoreKoin_coreKoinApplicationCompanion *companion __attribute__((swift_name("companion")));
- (void)allowOverrideOverride:(BOOL)override __attribute__((swift_name("allowOverride(override:)")));
- (void)close __attribute__((swift_name("close()")));
- (void)createEagerInstances __attribute__((swift_name("createEagerInstances()")));
- (MiamCoreKoin_coreKoinApplication *)loggerLogger:(MiamCoreKoin_coreLogger *)logger __attribute__((swift_name("logger(logger:)")));
- (MiamCoreKoin_coreKoinApplication *)modulesModules:(MiamCoreKotlinArray<MiamCoreKoin_coreModule *> *)modules __attribute__((swift_name("modules(modules:)")));
- (MiamCoreKoin_coreKoinApplication *)modulesModules_:(NSArray<MiamCoreKoin_coreModule *> *)modules __attribute__((swift_name("modules(modules_:)")));
- (MiamCoreKoin_coreKoinApplication *)modulesModules__:(MiamCoreKoin_coreModule *)modules __attribute__((swift_name("modules(modules__:)")));
- (MiamCoreKoin_coreKoinApplication *)printLoggerLevel:(MiamCoreKoin_coreLevel *)level __attribute__((swift_name("printLogger(level:)")));
- (MiamCoreKoin_coreKoinApplication *)propertiesValues:(NSDictionary<NSString *, id> *)values __attribute__((swift_name("properties(values:)")));
- (void)unloadModulesModules:(NSArray<MiamCoreKoin_coreModule *> *)modules __attribute__((swift_name("unloadModules(modules:)")));
- (void)unloadModulesModule:(MiamCoreKoin_coreModule *)module __attribute__((swift_name("unloadModules(module:)")));
@property (readonly) MiamCoreKoin_coreKoin *koin __attribute__((swift_name("koin")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreModule")))
@interface MiamCoreKoin_coreModule : MiamCoreBase
- (instancetype)initWith_createdAtStart:(BOOL)_createdAtStart __attribute__((swift_name("init(_createdAtStart:)"))) __attribute__((objc_designated_initializer));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (MiamCoreKotlinPair<MiamCoreKoin_coreModule *, MiamCoreKoin_coreInstanceFactory<id> *> *)factoryQualifier:(id<MiamCoreKoin_coreQualifier> _Nullable)qualifier definition:(id _Nullable (^)(MiamCoreKoin_coreScope *, MiamCoreKoin_coreParametersHolder *))definition __attribute__((swift_name("factory(qualifier:definition:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (void)includesModule:(MiamCoreKotlinArray<MiamCoreKoin_coreModule *> *)module __attribute__((swift_name("includes(module:)")));
- (void)includesModule_:(NSArray<MiamCoreKoin_coreModule *> *)module __attribute__((swift_name("includes(module_:)")));
- (NSArray<MiamCoreKoin_coreModule *> *)plusModules:(NSArray<MiamCoreKoin_coreModule *> *)modules __attribute__((swift_name("plus(modules:)")));
- (NSArray<MiamCoreKoin_coreModule *> *)plusModule:(MiamCoreKoin_coreModule *)module __attribute__((swift_name("plus(module:)")));
- (void)scopeQualifier:(id<MiamCoreKoin_coreQualifier>)qualifier scopeSet:(void (^)(MiamCoreKoin_coreScopeDSL *))scopeSet __attribute__((swift_name("scope(qualifier:scopeSet:)")));
- (void)scopeScopeSet:(void (^)(MiamCoreKoin_coreScopeDSL *))scopeSet __attribute__((swift_name("scope(scopeSet:)")));
- (MiamCoreKotlinPair<MiamCoreKoin_coreModule *, MiamCoreKoin_coreInstanceFactory<id> *> *)singleQualifier:(id<MiamCoreKoin_coreQualifier> _Nullable)qualifier createdAtStart:(BOOL)createdAtStart definition:(id _Nullable (^)(MiamCoreKoin_coreScope *, MiamCoreKoin_coreParametersHolder *))definition __attribute__((swift_name("single(qualifier:createdAtStart:definition:)")));
@property (readonly) MiamCoreMutableSet<MiamCoreKoin_coreSingleInstanceFactory<id> *> *eagerInstances __attribute__((swift_name("eagerInstances")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) BOOL isLoaded __attribute__((swift_name("isLoaded")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreSerialFormat")))
@protocol MiamCoreKotlinx_serialization_coreSerialFormat
@required
@property (readonly) MiamCoreKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreStringFormat")))
@protocol MiamCoreKotlinx_serialization_coreStringFormat <MiamCoreKotlinx_serialization_coreSerialFormat>
@required
- (id _Nullable)decodeFromStringDeserializer:(id<MiamCoreKotlinx_serialization_coreDeserializationStrategy>)deserializer string:(NSString *)string __attribute__((swift_name("decodeFromString(deserializer:string:)")));
- (NSString *)encodeToStringSerializer:(id<MiamCoreKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeToString(serializer:value:)")));
@end;

__attribute__((swift_name("Kotlinx_serialization_jsonJson")))
@interface MiamCoreKotlinx_serialization_jsonJson : MiamCoreBase <MiamCoreKotlinx_serialization_coreStringFormat>
- (instancetype)initWithConfiguration:(MiamCoreKotlinx_serialization_jsonJsonConfiguration *)configuration serializersModule:(MiamCoreKotlinx_serialization_coreSerializersModule *)serializersModule __attribute__((swift_name("init(configuration:serializersModule:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCoreKotlinx_serialization_jsonJsonDefault *companion __attribute__((swift_name("companion")));
- (id _Nullable)decodeFromJsonElementDeserializer:(id<MiamCoreKotlinx_serialization_coreDeserializationStrategy>)deserializer element:(MiamCoreKotlinx_serialization_jsonJsonElement *)element __attribute__((swift_name("decodeFromJsonElement(deserializer:element:)")));
- (id _Nullable)decodeFromStringDeserializer:(id<MiamCoreKotlinx_serialization_coreDeserializationStrategy>)deserializer string:(NSString *)string __attribute__((swift_name("decodeFromString(deserializer:string:)")));
- (MiamCoreKotlinx_serialization_jsonJsonElement *)encodeToJsonElementSerializer:(id<MiamCoreKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeToJsonElement(serializer:value:)")));
- (NSString *)encodeToStringSerializer:(id<MiamCoreKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeToString(serializer:value:)")));
- (MiamCoreKotlinx_serialization_jsonJsonElement *)parseToJsonElementString:(NSString *)string __attribute__((swift_name("parseToJsonElement(string:)")));
@property (readonly) MiamCoreKotlinx_serialization_jsonJsonConfiguration *configuration __attribute__((swift_name("configuration")));
@property (readonly) MiamCoreKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end;

__attribute__((swift_name("Koin_coreLockable")))
@interface MiamCoreKoin_coreLockable : MiamCoreBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreScope")))
@interface MiamCoreKoin_coreScope : MiamCoreKoin_coreLockable
- (instancetype)initWithScopeQualifier:(id<MiamCoreKoin_coreQualifier>)scopeQualifier id:(NSString *)id isRoot:(BOOL)isRoot _koin:(MiamCoreKoin_coreKoin *)_koin __attribute__((swift_name("init(scopeQualifier:id:isRoot:_koin:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (void)close __attribute__((swift_name("close()")));
- (id<MiamCoreKoin_coreQualifier>)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (BOOL)component3 __attribute__((swift_name("component3()")));
- (MiamCoreKoin_coreScope *)doCopyScopeQualifier:(id<MiamCoreKoin_coreQualifier>)scopeQualifier id:(NSString *)id isRoot:(BOOL)isRoot _koin:(MiamCoreKoin_coreKoin *)_koin __attribute__((swift_name("doCopy(scopeQualifier:id:isRoot:_koin:)")));
- (void)declareInstance:(id _Nullable)instance qualifier:(id<MiamCoreKoin_coreQualifier> _Nullable)qualifier secondaryTypes:(NSArray<id<MiamCoreKotlinKClass>> *)secondaryTypes allowOverride:(BOOL)allowOverride __attribute__((swift_name("declare(instance:qualifier:secondaryTypes:allowOverride:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (id _Nullable)getClazz:(id<MiamCoreKotlinKClass>)clazz qualifier:(id<MiamCoreKoin_coreQualifier> _Nullable)qualifier parameters:(MiamCoreKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("get(clazz:qualifier:parameters:)")));
- (id)getQualifier:(id<MiamCoreKoin_coreQualifier> _Nullable)qualifier parameters:(MiamCoreKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("get(qualifier:parameters:)")));
- (NSArray<id> *)getAll __attribute__((swift_name("getAll()")));
- (NSArray<id> *)getAllClazz:(id<MiamCoreKotlinKClass>)clazz __attribute__((swift_name("getAll(clazz:)")));
- (MiamCoreKoin_coreKoin *)getKoin __attribute__((swift_name("getKoin()")));
- (id _Nullable)getOrNullClazz:(id<MiamCoreKotlinKClass>)clazz qualifier:(id<MiamCoreKoin_coreQualifier> _Nullable)qualifier parameters:(MiamCoreKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("getOrNull(clazz:qualifier:parameters:)")));
- (id _Nullable)getOrNullQualifier:(id<MiamCoreKoin_coreQualifier> _Nullable)qualifier parameters:(MiamCoreKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("getOrNull(qualifier:parameters:)")));
- (id)getPropertyKey:(NSString *)key __attribute__((swift_name("getProperty(key:)")));
- (id)getPropertyKey:(NSString *)key defaultValue:(id)defaultValue __attribute__((swift_name("getProperty(key:defaultValue:)")));
- (id _Nullable)getPropertyOrNullKey:(NSString *)key __attribute__((swift_name("getPropertyOrNull(key:)")));
- (MiamCoreKoin_coreScope *)getScopeScopeID:(NSString *)scopeID __attribute__((swift_name("getScope(scopeID:)")));
- (id _Nullable)getSource __attribute__((swift_name("getSource()"))) __attribute__((deprecated("No need to use getSource(). You can an use get() directly.")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (id<MiamCoreKotlinLazy>)injectQualifier:(id<MiamCoreKoin_coreQualifier> _Nullable)qualifier mode:(MiamCoreKotlinLazyThreadSafetyMode *)mode parameters:(MiamCoreKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("inject(qualifier:mode:parameters:)")));
- (id<MiamCoreKotlinLazy>)injectOrNullQualifier:(id<MiamCoreKoin_coreQualifier> _Nullable)qualifier mode:(MiamCoreKotlinLazyThreadSafetyMode *)mode parameters:(MiamCoreKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("injectOrNull(qualifier:mode:parameters:)")));
- (BOOL)isNotClosed __attribute__((swift_name("isNotClosed()")));
- (void)linkToScopes:(MiamCoreKotlinArray<MiamCoreKoin_coreScope *> *)scopes __attribute__((swift_name("linkTo(scopes:)")));
- (void)registerCallbackCallback:(id<MiamCoreKoin_coreScopeCallback>)callback __attribute__((swift_name("registerCallback(callback:)")));
- (NSString *)description __attribute__((swift_name("description()")));
- (void)unlinkScopes:(MiamCoreKotlinArray<MiamCoreKoin_coreScope *> *)scopes __attribute__((swift_name("unlink(scopes:)")));
@property (readonly) NSMutableArray<MiamCoreKoin_coreParametersHolder *> *_parameterStack __attribute__((swift_name("_parameterStack")));
@property id _Nullable _source __attribute__((swift_name("_source")));
@property (readonly) BOOL closed __attribute__((swift_name("closed")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) BOOL isRoot __attribute__((swift_name("isRoot")));
@property (readonly) MiamCoreKoin_coreLogger *logger __attribute__((swift_name("logger")));
@property (readonly) id<MiamCoreKoin_coreQualifier> scopeQualifier __attribute__((swift_name("scopeQualifier")));
@end;

__attribute__((swift_name("Koin_coreKoinScopeComponent")))
@protocol MiamCoreKoin_coreKoinScopeComponent <MiamCoreKoin_coreKoinComponent>
@required
- (void)closeScope __attribute__((swift_name("closeScope()")));
@property (readonly) MiamCoreKoin_coreScope *scope __attribute__((swift_name("scope")));
@end;

__attribute__((swift_name("Koin_coreQualifier")))
@protocol MiamCoreKoin_coreQualifier
@required
@property (readonly) NSString *value __attribute__((swift_name("value")));
@end;

__attribute__((swift_name("Koin_coreParametersHolder")))
@interface MiamCoreKoin_coreParametersHolder : MiamCoreBase
- (instancetype)initWith_values:(NSMutableArray<id> *)_values __attribute__((swift_name("init(_values:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCoreKoin_coreParametersHolderCompanion *companion __attribute__((swift_name("companion")));
- (MiamCoreKoin_coreParametersHolder *)addValue:(id)value __attribute__((swift_name("add(value:)")));
- (id _Nullable)component1 __attribute__((swift_name("component1()")));
- (id _Nullable)component2 __attribute__((swift_name("component2()")));
- (id _Nullable)component3 __attribute__((swift_name("component3()")));
- (id _Nullable)component4 __attribute__((swift_name("component4()")));
- (id _Nullable)component5 __attribute__((swift_name("component5()")));
- (id _Nullable)elementAtI:(int32_t)i clazz:(id<MiamCoreKotlinKClass>)clazz __attribute__((swift_name("elementAt(i:clazz:)")));
- (id)get __attribute__((swift_name("get()")));
- (id _Nullable)getI:(int32_t)i __attribute__((swift_name("get(i:)")));
- (id _Nullable)getOrNull __attribute__((swift_name("getOrNull()")));
- (id _Nullable)getOrNullClazz:(id<MiamCoreKotlinKClass>)clazz __attribute__((swift_name("getOrNull(clazz:)")));
- (MiamCoreKoin_coreParametersHolder *)insertIndex:(int32_t)index value:(id)value __attribute__((swift_name("insert(index:value:)")));
- (BOOL)isEmpty __attribute__((swift_name("isEmpty()")));
- (BOOL)isNotEmpty __attribute__((swift_name("isNotEmpty()")));
- (void)setI:(int32_t)i t:(id _Nullable)t __attribute__((swift_name("set(i:t:)")));
- (int32_t)size __attribute__((swift_name("size()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<id> *values __attribute__((swift_name("values")));
@end;

__attribute__((swift_name("KotlinLazy")))
@protocol MiamCoreKotlinLazy
@required
- (BOOL)isInitialized __attribute__((swift_name("isInitialized()")));
@property (readonly) id _Nullable value __attribute__((swift_name("value")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinLazyThreadSafetyMode")))
@interface MiamCoreKotlinLazyThreadSafetyMode : MiamCoreKotlinEnum<MiamCoreKotlinLazyThreadSafetyMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) MiamCoreKotlinLazyThreadSafetyMode *synchronized __attribute__((swift_name("synchronized")));
@property (class, readonly) MiamCoreKotlinLazyThreadSafetyMode *publication __attribute__((swift_name("publication")));
@property (class, readonly) MiamCoreKotlinLazyThreadSafetyMode *none __attribute__((swift_name("none")));
+ (MiamCoreKotlinArray<MiamCoreKotlinLazyThreadSafetyMode *> *)values __attribute__((swift_name("values()")));
@end;

__attribute__((swift_name("Koin_coreLogger")))
@interface MiamCoreKoin_coreLogger : MiamCoreBase
- (instancetype)initWithLevel:(MiamCoreKoin_coreLevel *)level __attribute__((swift_name("init(level:)"))) __attribute__((objc_designated_initializer));
- (void)debugMsg:(NSString *)msg __attribute__((swift_name("debug(msg:)")));
- (void)errorMsg:(NSString *)msg __attribute__((swift_name("error(msg:)")));
- (void)infoMsg:(NSString *)msg __attribute__((swift_name("info(msg:)")));
- (BOOL)isAtLvl:(MiamCoreKoin_coreLevel *)lvl __attribute__((swift_name("isAt(lvl:)")));
- (void)logLvl:(MiamCoreKoin_coreLevel *)lvl msg:(NSString *(^)(void))msg __attribute__((swift_name("log(lvl:msg:)")));
- (void)logLevel:(MiamCoreKoin_coreLevel *)level msg:(NSString *)msg __attribute__((swift_name("log(level:msg:)")));
@property MiamCoreKoin_coreLevel *level __attribute__((swift_name("level")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreInstanceRegistry")))
@interface MiamCoreKoin_coreInstanceRegistry : MiamCoreBase
- (instancetype)initWith_koin:(MiamCoreKoin_coreKoin *)_koin __attribute__((swift_name("init(_koin:)"))) __attribute__((objc_designated_initializer));
- (void)saveMappingAllowOverride:(BOOL)allowOverride mapping:(NSString *)mapping factory:(MiamCoreKoin_coreInstanceFactory<id> *)factory logWarning:(BOOL)logWarning __attribute__((swift_name("saveMapping(allowOverride:mapping:factory:logWarning:)")));
- (int32_t)size __attribute__((swift_name("size()")));
@property (readonly) MiamCoreKoin_coreKoin *_koin __attribute__((swift_name("_koin")));
@property (readonly) NSDictionary<NSString *, MiamCoreKoin_coreInstanceFactory<id> *> *instances __attribute__((swift_name("instances")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_corePropertyRegistry")))
@interface MiamCoreKoin_corePropertyRegistry : MiamCoreBase
- (instancetype)initWith_koin:(MiamCoreKoin_coreKoin *)_koin __attribute__((swift_name("init(_koin:)"))) __attribute__((objc_designated_initializer));
- (void)close __attribute__((swift_name("close()")));
- (void)deletePropertyKey:(NSString *)key __attribute__((swift_name("deleteProperty(key:)")));
- (id _Nullable)getPropertyKey:(NSString *)key __attribute__((swift_name("getProperty(key:)")));
- (void)savePropertiesProperties:(NSDictionary<NSString *, id> *)properties __attribute__((swift_name("saveProperties(properties:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreScopeRegistry")))
@interface MiamCoreKoin_coreScopeRegistry : MiamCoreBase
- (instancetype)initWith_koin:(MiamCoreKoin_coreKoin *)_koin __attribute__((swift_name("init(_koin:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) MiamCoreKoin_coreScopeRegistryCompanion *companion __attribute__((swift_name("companion")));
- (void)loadScopesModules:(NSSet<MiamCoreKoin_coreModule *> *)modules __attribute__((swift_name("loadScopes(modules:)")));
@property (readonly) MiamCoreKoin_coreScope *rootScope __attribute__((swift_name("rootScope")));
@property (readonly) NSSet<id<MiamCoreKoin_coreQualifier>> *scopeDefinitions __attribute__((swift_name("scopeDefinitions")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreDisposableHandle")))
@protocol MiamCoreKotlinx_coroutines_coreDisposableHandle
@required
- (void)dispose __attribute__((swift_name("dispose()")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreChildHandle")))
@protocol MiamCoreKotlinx_coroutines_coreChildHandle <MiamCoreKotlinx_coroutines_coreDisposableHandle>
@required
- (BOOL)childCancelledCause:(MiamCoreKotlinThrowable *)cause __attribute__((swift_name("childCancelled(cause:)")));
@property (readonly) id<MiamCoreKotlinx_coroutines_coreJob> _Nullable parent __attribute__((swift_name("parent")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreChildJob")))
@protocol MiamCoreKotlinx_coroutines_coreChildJob <MiamCoreKotlinx_coroutines_coreJob>
@required
- (void)parentCancelledParentJob:(id<MiamCoreKotlinx_coroutines_coreParentJob>)parentJob __attribute__((swift_name("parentCancelled(parentJob:)")));
@end;

__attribute__((swift_name("KotlinSequence")))
@protocol MiamCoreKotlinSequence
@required
- (id<MiamCoreKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreSelectClause0")))
@protocol MiamCoreKotlinx_coroutines_coreSelectClause0
@required
- (void)registerSelectClause0Select:(id<MiamCoreKotlinx_coroutines_coreSelectInstance>)select block:(id<MiamCoreKotlinSuspendFunction0>)block __attribute__((swift_name("registerSelectClause0(select:block:)")));
@end;

__attribute__((swift_name("KotlinIterator")))
@protocol MiamCoreKotlinIterator
@required
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (id _Nullable)next __attribute__((swift_name("next()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_serialization_jsonJsonElement.Companion")))
@interface MiamCoreKotlinx_serialization_jsonJsonElementCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreKotlinx_serialization_jsonJsonElementCompanion *shared __attribute__((swift_name("shared")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreCompositeEncoder")))
@protocol MiamCoreKotlinx_serialization_coreCompositeEncoder
@required
- (void)encodeBooleanElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(BOOL)value __attribute__((swift_name("encodeBooleanElement(descriptor:index:value:)")));
- (void)encodeByteElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int8_t)value __attribute__((swift_name("encodeByteElement(descriptor:index:value:)")));
- (void)encodeCharElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(unichar)value __attribute__((swift_name("encodeCharElement(descriptor:index:value:)")));
- (void)encodeDoubleElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(double)value __attribute__((swift_name("encodeDoubleElement(descriptor:index:value:)")));
- (void)encodeFloatElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(float)value __attribute__((swift_name("encodeFloatElement(descriptor:index:value:)")));
- (id<MiamCoreKotlinx_serialization_coreEncoder>)encodeInlineElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("encodeInlineElement(descriptor:index:)")));
- (void)encodeIntElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int32_t)value __attribute__((swift_name("encodeIntElement(descriptor:index:value:)")));
- (void)encodeLongElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int64_t)value __attribute__((swift_name("encodeLongElement(descriptor:index:value:)")));
- (void)encodeNullableSerializableElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index serializer:(id<MiamCoreKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableElement(descriptor:index:serializer:value:)")));
- (void)encodeSerializableElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index serializer:(id<MiamCoreKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableElement(descriptor:index:serializer:value:)")));
- (void)encodeShortElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int16_t)value __attribute__((swift_name("encodeShortElement(descriptor:index:value:)")));
- (void)encodeStringElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(NSString *)value __attribute__((swift_name("encodeStringElement(descriptor:index:value:)")));
- (void)endStructureDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("endStructure(descriptor:)")));
- (BOOL)shouldEncodeElementDefaultDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("shouldEncodeElementDefault(descriptor:index:)")));
@property (readonly) MiamCoreKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreSerializersModule")))
@interface MiamCoreKotlinx_serialization_coreSerializersModule : MiamCoreBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)dumpToCollector:(id<MiamCoreKotlinx_serialization_coreSerializersModuleCollector>)collector __attribute__((swift_name("dumpTo(collector:)")));
- (id<MiamCoreKotlinx_serialization_coreKSerializer> _Nullable)getContextualKClass:(id<MiamCoreKotlinKClass>)kClass typeArgumentsSerializers:(NSArray<id<MiamCoreKotlinx_serialization_coreKSerializer>> *)typeArgumentsSerializers __attribute__((swift_name("getContextual(kClass:typeArgumentsSerializers:)")));
- (id<MiamCoreKotlinx_serialization_coreSerializationStrategy> _Nullable)getPolymorphicBaseClass:(id<MiamCoreKotlinKClass>)baseClass value:(id)value __attribute__((swift_name("getPolymorphic(baseClass:value:)")));
- (id<MiamCoreKotlinx_serialization_coreDeserializationStrategy> _Nullable)getPolymorphicBaseClass:(id<MiamCoreKotlinKClass>)baseClass serializedClassName:(NSString * _Nullable)serializedClassName __attribute__((swift_name("getPolymorphic(baseClass:serializedClassName:)")));
@end;

__attribute__((swift_name("KotlinAnnotation")))
@protocol MiamCoreKotlinAnnotation
@required
@end;

__attribute__((swift_name("Kotlinx_serialization_coreSerialKind")))
@interface MiamCoreKotlinx_serialization_coreSerialKind : MiamCoreBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreCompositeDecoder")))
@protocol MiamCoreKotlinx_serialization_coreCompositeDecoder
@required
- (BOOL)decodeBooleanElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeBooleanElement(descriptor:index:)")));
- (int8_t)decodeByteElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeByteElement(descriptor:index:)")));
- (unichar)decodeCharElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeCharElement(descriptor:index:)")));
- (int32_t)decodeCollectionSizeDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeCollectionSize(descriptor:)")));
- (double)decodeDoubleElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeDoubleElement(descriptor:index:)")));
- (int32_t)decodeElementIndexDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeElementIndex(descriptor:)")));
- (float)decodeFloatElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeFloatElement(descriptor:index:)")));
- (id<MiamCoreKotlinx_serialization_coreDecoder>)decodeInlineElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeInlineElement(descriptor:index:)")));
- (int32_t)decodeIntElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeIntElement(descriptor:index:)")));
- (int64_t)decodeLongElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeLongElement(descriptor:index:)")));
- (id _Nullable)decodeNullableSerializableElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index deserializer:(id<MiamCoreKotlinx_serialization_coreDeserializationStrategy>)deserializer previousValue:(id _Nullable)previousValue __attribute__((swift_name("decodeNullableSerializableElement(descriptor:index:deserializer:previousValue:)")));
- (BOOL)decodeSequentially __attribute__((swift_name("decodeSequentially()")));
- (id _Nullable)decodeSerializableElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index deserializer:(id<MiamCoreKotlinx_serialization_coreDeserializationStrategy>)deserializer previousValue:(id _Nullable)previousValue __attribute__((swift_name("decodeSerializableElement(descriptor:index:deserializer:previousValue:)")));
- (int16_t)decodeShortElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeShortElement(descriptor:index:)")));
- (NSString *)decodeStringElementDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeStringElement(descriptor:index:)")));
- (void)endStructureDescriptor:(id<MiamCoreKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("endStructure(descriptor:)")));
@property (readonly) MiamCoreKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreKoinApplication.Companion")))
@interface MiamCoreKoin_coreKoinApplicationCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreKoin_coreKoinApplicationCompanion *shared __attribute__((swift_name("shared")));
- (MiamCoreKoin_coreKoinApplication *)doInit __attribute__((swift_name("doInit()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreLevel")))
@interface MiamCoreKoin_coreLevel : MiamCoreKotlinEnum<MiamCoreKoin_coreLevel *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) MiamCoreKoin_coreLevel *debug __attribute__((swift_name("debug")));
@property (class, readonly) MiamCoreKoin_coreLevel *info __attribute__((swift_name("info")));
@property (class, readonly) MiamCoreKoin_coreLevel *error __attribute__((swift_name("error")));
@property (class, readonly) MiamCoreKoin_coreLevel *none __attribute__((swift_name("none")));
+ (MiamCoreKotlinArray<MiamCoreKoin_coreLevel *> *)values __attribute__((swift_name("values()")));
@end;

__attribute__((swift_name("Koin_coreInstanceFactory")))
@interface MiamCoreKoin_coreInstanceFactory<T> : MiamCoreKoin_coreLockable
- (instancetype)initWithBeanDefinition:(MiamCoreKoin_coreBeanDefinition<T> *)beanDefinition __attribute__((swift_name("init(beanDefinition:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) MiamCoreKoin_coreInstanceFactoryCompanion *companion __attribute__((swift_name("companion")));
- (T _Nullable)createContext:(MiamCoreKoin_coreInstanceContext *)context __attribute__((swift_name("create(context:)")));
- (void)dropScope:(MiamCoreKoin_coreScope * _Nullable)scope __attribute__((swift_name("drop(scope:)")));
- (void)dropAll __attribute__((swift_name("dropAll()")));
- (T _Nullable)getContext:(MiamCoreKoin_coreInstanceContext *)context __attribute__((swift_name("get(context:)")));
- (BOOL)isCreatedContext:(MiamCoreKoin_coreInstanceContext * _Nullable)context __attribute__((swift_name("isCreated(context:)")));
@property (readonly) MiamCoreKoin_coreBeanDefinition<T> *beanDefinition __attribute__((swift_name("beanDefinition")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinPair")))
@interface MiamCoreKotlinPair<__covariant A, __covariant B> : MiamCoreBase
- (instancetype)initWithFirst:(A _Nullable)first second:(B _Nullable)second __attribute__((swift_name("init(first:second:)"))) __attribute__((objc_designated_initializer));
- (A _Nullable)component1 __attribute__((swift_name("component1()")));
- (B _Nullable)component2 __attribute__((swift_name("component2()")));
- (MiamCoreKotlinPair<A, B> *)doCopyFirst:(A _Nullable)first second:(B _Nullable)second __attribute__((swift_name("doCopy(first:second:)")));
- (BOOL)equalsOther:(id _Nullable)other __attribute__((swift_name("equals(other:)")));
- (int32_t)hashCode __attribute__((swift_name("hashCode()")));
- (NSString *)toString __attribute__((swift_name("toString()")));
@property (readonly) A _Nullable first __attribute__((swift_name("first")));
@property (readonly) B _Nullable second __attribute__((swift_name("second")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreScopeDSL")))
@interface MiamCoreKoin_coreScopeDSL : MiamCoreBase
- (instancetype)initWithScopeQualifier:(id<MiamCoreKoin_coreQualifier>)scopeQualifier module:(MiamCoreKoin_coreModule *)module __attribute__((swift_name("init(scopeQualifier:module:)"))) __attribute__((objc_designated_initializer));
- (MiamCoreKotlinPair<MiamCoreKoin_coreModule *, MiamCoreKoin_coreInstanceFactory<id> *> *)factoryQualifier:(id<MiamCoreKoin_coreQualifier> _Nullable)qualifier definition:(id _Nullable (^)(MiamCoreKoin_coreScope *, MiamCoreKoin_coreParametersHolder *))definition __attribute__((swift_name("factory(qualifier:definition:)")));
- (MiamCoreKotlinPair<MiamCoreKoin_coreModule *, MiamCoreKoin_coreInstanceFactory<id> *> *)scopedQualifier:(id<MiamCoreKoin_coreQualifier> _Nullable)qualifier definition:(id _Nullable (^)(MiamCoreKoin_coreScope *, MiamCoreKoin_coreParametersHolder *))definition __attribute__((swift_name("scoped(qualifier:definition:)")));
- (MiamCoreKotlinPair<MiamCoreKoin_coreModule *, MiamCoreKoin_coreInstanceFactory<id> *> *)singleQualifier:(id<MiamCoreKoin_coreQualifier> _Nullable)qualifier definition:(id _Nullable (^)(MiamCoreKoin_coreScope *, MiamCoreKoin_coreParametersHolder *))definition __attribute__((swift_name("single(qualifier:definition:)"))) __attribute__((unavailable("Can't use Single in a scope. Use Scoped instead")));
@property (readonly) MiamCoreKoin_coreModule *module __attribute__((swift_name("module")));
@property (readonly) id<MiamCoreKoin_coreQualifier> scopeQualifier __attribute__((swift_name("scopeQualifier")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreSingleInstanceFactory")))
@interface MiamCoreKoin_coreSingleInstanceFactory<T> : MiamCoreKoin_coreInstanceFactory<T>
- (instancetype)initWithBeanDefinition:(MiamCoreKoin_coreBeanDefinition<T> *)beanDefinition __attribute__((swift_name("init(beanDefinition:)"))) __attribute__((objc_designated_initializer));
- (T _Nullable)createContext:(MiamCoreKoin_coreInstanceContext *)context __attribute__((swift_name("create(context:)")));
- (void)dropScope:(MiamCoreKoin_coreScope * _Nullable)scope __attribute__((swift_name("drop(scope:)")));
- (void)dropAll __attribute__((swift_name("dropAll()")));
- (T _Nullable)getContext:(MiamCoreKoin_coreInstanceContext *)context __attribute__((swift_name("get(context:)")));
- (BOOL)isCreatedContext:(MiamCoreKoin_coreInstanceContext * _Nullable)context __attribute__((swift_name("isCreated(context:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_serialization_jsonJsonConfiguration")))
@interface MiamCoreKotlinx_serialization_jsonJsonConfiguration : MiamCoreBase
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL allowSpecialFloatingPointValues __attribute__((swift_name("allowSpecialFloatingPointValues")));
@property (readonly) BOOL allowStructuredMapKeys __attribute__((swift_name("allowStructuredMapKeys")));
@property (readonly) NSString *classDiscriminator __attribute__((swift_name("classDiscriminator")));
@property (readonly) BOOL coerceInputValues __attribute__((swift_name("coerceInputValues")));
@property (readonly) BOOL encodeDefaults __attribute__((swift_name("encodeDefaults")));
@property (readonly) BOOL explicitNulls __attribute__((swift_name("explicitNulls")));
@property (readonly) BOOL ignoreUnknownKeys __attribute__((swift_name("ignoreUnknownKeys")));
@property (readonly) BOOL isLenient __attribute__((swift_name("isLenient")));
@property (readonly) BOOL prettyPrint __attribute__((swift_name("prettyPrint")));
@property (readonly) NSString *prettyPrintIndent __attribute__((swift_name("prettyPrintIndent")));
@property (readonly) BOOL useAlternativeNames __attribute__((swift_name("useAlternativeNames")));
@property (readonly) BOOL useArrayPolymorphism __attribute__((swift_name("useArrayPolymorphism")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_serialization_jsonJson.Default")))
@interface MiamCoreKotlinx_serialization_jsonJsonDefault : MiamCoreKotlinx_serialization_jsonJson
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithConfiguration:(MiamCoreKotlinx_serialization_jsonJsonConfiguration *)configuration serializersModule:(MiamCoreKotlinx_serialization_coreSerializersModule *)serializersModule __attribute__((swift_name("init(configuration:serializersModule:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)default_ __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreKotlinx_serialization_jsonJsonDefault *shared __attribute__((swift_name("shared")));
@end;

__attribute__((swift_name("Koin_coreScopeCallback")))
@protocol MiamCoreKoin_coreScopeCallback
@required
- (void)onScopeCloseScope:(MiamCoreKoin_coreScope *)scope __attribute__((swift_name("onScopeClose(scope:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreParametersHolder.Companion")))
@interface MiamCoreKoin_coreParametersHolderCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreKoin_coreParametersHolderCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) int32_t MAX_PARAMS __attribute__((swift_name("MAX_PARAMS")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreScopeRegistry.Companion")))
@interface MiamCoreKoin_coreScopeRegistryCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreKoin_coreScopeRegistryCompanion *shared __attribute__((swift_name("shared")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreParentJob")))
@protocol MiamCoreKotlinx_coroutines_coreParentJob <MiamCoreKotlinx_coroutines_coreJob>
@required
- (MiamCoreKotlinCancellationException *)getChildJobCancellationCause __attribute__((swift_name("getChildJobCancellationCause()")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreSelectInstance")))
@protocol MiamCoreKotlinx_coroutines_coreSelectInstance
@required
- (void)disposeOnSelectHandle:(id<MiamCoreKotlinx_coroutines_coreDisposableHandle>)handle __attribute__((swift_name("disposeOnSelect(handle:)")));
- (id _Nullable)performAtomicTrySelectDesc:(MiamCoreKotlinx_coroutines_coreAtomicDesc *)desc __attribute__((swift_name("performAtomicTrySelect(desc:)")));
- (void)resumeSelectWithExceptionException:(MiamCoreKotlinThrowable *)exception __attribute__((swift_name("resumeSelectWithException(exception:)")));
- (BOOL)trySelect __attribute__((swift_name("trySelect()")));
- (id _Nullable)trySelectOtherOtherOp:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNodePrepareOp * _Nullable)otherOp __attribute__((swift_name("trySelectOther(otherOp:)")));
@property (readonly) id<MiamCoreKotlinContinuation> completion __attribute__((swift_name("completion")));
@property (readonly) BOOL isSelected __attribute__((swift_name("isSelected")));
@end;

__attribute__((swift_name("KotlinFunction")))
@protocol MiamCoreKotlinFunction
@required
@end;

__attribute__((swift_name("KotlinSuspendFunction0")))
@protocol MiamCoreKotlinSuspendFunction0 <MiamCoreKotlinFunction>
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)invokeWithCompletionHandler:(void (^)(id _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("invoke(completionHandler:)")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreSerializersModuleCollector")))
@protocol MiamCoreKotlinx_serialization_coreSerializersModuleCollector
@required
- (void)contextualKClass:(id<MiamCoreKotlinKClass>)kClass provider:(id<MiamCoreKotlinx_serialization_coreKSerializer> (^)(NSArray<id<MiamCoreKotlinx_serialization_coreKSerializer>> *))provider __attribute__((swift_name("contextual(kClass:provider:)")));
- (void)contextualKClass:(id<MiamCoreKotlinKClass>)kClass serializer:(id<MiamCoreKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("contextual(kClass:serializer:)")));
- (void)polymorphicBaseClass:(id<MiamCoreKotlinKClass>)baseClass actualClass:(id<MiamCoreKotlinKClass>)actualClass actualSerializer:(id<MiamCoreKotlinx_serialization_coreKSerializer>)actualSerializer __attribute__((swift_name("polymorphic(baseClass:actualClass:actualSerializer:)")));
- (void)polymorphicDefaultBaseClass:(id<MiamCoreKotlinKClass>)baseClass defaultSerializerProvider:(id<MiamCoreKotlinx_serialization_coreDeserializationStrategy> _Nullable (^)(NSString * _Nullable))defaultSerializerProvider __attribute__((swift_name("polymorphicDefault(baseClass:defaultSerializerProvider:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreBeanDefinition")))
@interface MiamCoreKoin_coreBeanDefinition<T> : MiamCoreBase
- (instancetype)initWithScopeQualifier:(id<MiamCoreKoin_coreQualifier>)scopeQualifier primaryType:(id<MiamCoreKotlinKClass>)primaryType qualifier:(id<MiamCoreKoin_coreQualifier> _Nullable)qualifier definition:(T _Nullable (^)(MiamCoreKoin_coreScope *, MiamCoreKoin_coreParametersHolder *))definition kind:(MiamCoreKoin_coreKind *)kind secondaryTypes:(NSArray<id<MiamCoreKotlinKClass>> *)secondaryTypes __attribute__((swift_name("init(scopeQualifier:primaryType:qualifier:definition:kind:secondaryTypes:)"))) __attribute__((objc_designated_initializer));
- (id<MiamCoreKoin_coreQualifier>)component1 __attribute__((swift_name("component1()")));
- (id<MiamCoreKotlinKClass>)component2 __attribute__((swift_name("component2()")));
- (id<MiamCoreKoin_coreQualifier> _Nullable)component3 __attribute__((swift_name("component3()")));
- (T _Nullable (^)(MiamCoreKoin_coreScope *, MiamCoreKoin_coreParametersHolder *))component4 __attribute__((swift_name("component4()")));
- (MiamCoreKoin_coreKind *)component5 __attribute__((swift_name("component5()")));
- (NSArray<id<MiamCoreKotlinKClass>> *)component6 __attribute__((swift_name("component6()")));
- (MiamCoreKoin_coreBeanDefinition<T> *)doCopyScopeQualifier:(id<MiamCoreKoin_coreQualifier>)scopeQualifier primaryType:(id<MiamCoreKotlinKClass>)primaryType qualifier:(id<MiamCoreKoin_coreQualifier> _Nullable)qualifier definition:(T _Nullable (^)(MiamCoreKoin_coreScope *, MiamCoreKoin_coreParametersHolder *))definition kind:(MiamCoreKoin_coreKind *)kind secondaryTypes:(NSArray<id<MiamCoreKotlinKClass>> *)secondaryTypes __attribute__((swift_name("doCopy(scopeQualifier:primaryType:qualifier:definition:kind:secondaryTypes:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (BOOL)hasTypeClazz:(id<MiamCoreKotlinKClass>)clazz __attribute__((swift_name("hasType(clazz:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (BOOL)isClazz:(id<MiamCoreKotlinKClass>)clazz qualifier:(id<MiamCoreKoin_coreQualifier> _Nullable)qualifier scopeDefinition:(id<MiamCoreKoin_coreQualifier>)scopeDefinition __attribute__((swift_name("is(clazz:qualifier:scopeDefinition:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property MiamCoreKoin_coreCallbacks<T> *callbacks __attribute__((swift_name("callbacks")));
@property (readonly) T _Nullable (^definition)(MiamCoreKoin_coreScope *, MiamCoreKoin_coreParametersHolder *) __attribute__((swift_name("definition")));
@property (readonly) MiamCoreKoin_coreKind *kind __attribute__((swift_name("kind")));
@property (readonly) id<MiamCoreKotlinKClass> primaryType __attribute__((swift_name("primaryType")));
@property id<MiamCoreKoin_coreQualifier> _Nullable qualifier __attribute__((swift_name("qualifier")));
@property (readonly) id<MiamCoreKoin_coreQualifier> scopeQualifier __attribute__((swift_name("scopeQualifier")));
@property NSArray<id<MiamCoreKotlinKClass>> *secondaryTypes __attribute__((swift_name("secondaryTypes")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreInstanceFactoryCompanion")))
@interface MiamCoreKoin_coreInstanceFactoryCompanion : MiamCoreBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) MiamCoreKoin_coreInstanceFactoryCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) NSString *ERROR_SEPARATOR __attribute__((swift_name("ERROR_SEPARATOR")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreInstanceContext")))
@interface MiamCoreKoin_coreInstanceContext : MiamCoreBase
- (instancetype)initWithKoin:(MiamCoreKoin_coreKoin *)koin scope:(MiamCoreKoin_coreScope *)scope parameters:(MiamCoreKoin_coreParametersHolder * _Nullable)parameters __attribute__((swift_name("init(koin:scope:parameters:)"))) __attribute__((objc_designated_initializer));
@property (readonly) MiamCoreKoin_coreKoin *koin __attribute__((swift_name("koin")));
@property (readonly) MiamCoreKoin_coreParametersHolder * _Nullable parameters __attribute__((swift_name("parameters")));
@property (readonly) MiamCoreKoin_coreScope *scope __attribute__((swift_name("scope")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreAtomicDesc")))
@interface MiamCoreKotlinx_coroutines_coreAtomicDesc : MiamCoreBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)completeOp:(MiamCoreKotlinx_coroutines_coreAtomicOp<id> *)op failure:(id _Nullable)failure __attribute__((swift_name("complete(op:failure:)")));
- (id _Nullable)prepareOp:(MiamCoreKotlinx_coroutines_coreAtomicOp<id> *)op __attribute__((swift_name("prepare(op:)")));
@property MiamCoreKotlinx_coroutines_coreAtomicOp<id> *atomicOp __attribute__((swift_name("atomicOp")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreOpDescriptor")))
@interface MiamCoreKotlinx_coroutines_coreOpDescriptor : MiamCoreBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (BOOL)isEarlierThanThat:(MiamCoreKotlinx_coroutines_coreOpDescriptor *)that __attribute__((swift_name("isEarlierThan(that:)")));
- (id _Nullable)performAffected:(id _Nullable)affected __attribute__((swift_name("perform(affected:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreKotlinx_coroutines_coreAtomicOp<id> * _Nullable atomicOp __attribute__((swift_name("atomicOp")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_coroutines_coreLockFreeLinkedListNode.PrepareOp")))
@interface MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNodePrepareOp : MiamCoreKotlinx_coroutines_coreOpDescriptor
- (instancetype)initWithAffected:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)next desc:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNodeAbstractAtomicDesc *)desc __attribute__((swift_name("init(affected:next:desc:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (void)finishPrepare __attribute__((swift_name("finishPrepare()")));
- (id _Nullable)performAffected:(id _Nullable)affected __attribute__((swift_name("perform(affected:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *affected __attribute__((swift_name("affected")));
@property (readonly) MiamCoreKotlinx_coroutines_coreAtomicOp<id> *atomicOp __attribute__((swift_name("atomicOp")));
@property (readonly) MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNodeAbstractAtomicDesc *desc __attribute__((swift_name("desc")));
@property (readonly) MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *next __attribute__((swift_name("next")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreKind")))
@interface MiamCoreKoin_coreKind : MiamCoreKotlinEnum<MiamCoreKoin_coreKind *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) MiamCoreKoin_coreKind *singleton __attribute__((swift_name("singleton")));
@property (class, readonly) MiamCoreKoin_coreKind *factory __attribute__((swift_name("factory")));
@property (class, readonly) MiamCoreKoin_coreKind *scoped __attribute__((swift_name("scoped")));
+ (MiamCoreKotlinArray<MiamCoreKoin_coreKind *> *)values __attribute__((swift_name("values()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreCallbacks")))
@interface MiamCoreKoin_coreCallbacks<T> : MiamCoreBase
- (instancetype)initWithOnClose:(void (^ _Nullable)(T _Nullable))onClose __attribute__((swift_name("init(onClose:)"))) __attribute__((objc_designated_initializer));
- (void (^ _Nullable)(T _Nullable))component1 __attribute__((swift_name("component1()")));
- (MiamCoreKoin_coreCallbacks<T> *)doCopyOnClose:(void (^ _Nullable)(T _Nullable))onClose __attribute__((swift_name("doCopy(onClose:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) void (^ _Nullable onClose)(T _Nullable) __attribute__((swift_name("onClose")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreAtomicOp")))
@interface MiamCoreKotlinx_coroutines_coreAtomicOp<__contravariant T> : MiamCoreKotlinx_coroutines_coreOpDescriptor
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)completeAffected:(T _Nullable)affected failure:(id _Nullable)failure __attribute__((swift_name("complete(affected:failure:)")));
- (id _Nullable)decideDecision:(id _Nullable)decision __attribute__((swift_name("decide(decision:)")));
- (id _Nullable)performAffected:(id _Nullable)affected __attribute__((swift_name("perform(affected:)")));
- (id _Nullable)prepareAffected:(T _Nullable)affected __attribute__((swift_name("prepare(affected:)")));
@property (readonly) MiamCoreKotlinx_coroutines_coreAtomicOp<id> *atomicOp __attribute__((swift_name("atomicOp")));
@property (readonly) id _Nullable consensus __attribute__((swift_name("consensus")));
@property (readonly) BOOL isDecided __attribute__((swift_name("isDecided")));
@property (readonly) int64_t opSequence __attribute__((swift_name("opSequence")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreLockFreeLinkedListNode")))
@interface MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode : MiamCoreBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)addLastNode:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)node __attribute__((swift_name("addLast(node:)")));
- (BOOL)addLastIfNode:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)node condition:(MiamCoreBoolean *(^)(void))condition __attribute__((swift_name("addLastIf(node:condition:)")));
- (BOOL)addLastIfPrevNode:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)node predicate:(MiamCoreBoolean *(^)(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *))predicate __attribute__((swift_name("addLastIfPrev(node:predicate:)")));
- (BOOL)addLastIfPrevAndIfNode:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)node predicate:(MiamCoreBoolean *(^)(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *))predicate condition:(MiamCoreBoolean *(^)(void))condition __attribute__((swift_name("addLastIfPrevAndIf(node:predicate:condition:)")));
- (BOOL)addOneIfEmptyNode:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)node __attribute__((swift_name("addOneIfEmpty(node:)")));
- (MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNodeAddLastDesc<MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *> *)describeAddLastNode:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)node __attribute__((swift_name("describeAddLast(node:)")));
- (MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNodeRemoveFirstDesc<MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *> *)describeRemoveFirst __attribute__((swift_name("describeRemoveFirst()")));
- (void)helpRemove __attribute__((swift_name("helpRemove()")));
- (MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable)nextIfRemoved __attribute__((swift_name("nextIfRemoved()")));
- (BOOL)remove __attribute__((swift_name("remove()")));
- (id _Nullable)removeFirstIfIsInstanceOfOrPeekIfPredicate:(MiamCoreBoolean *(^)(id _Nullable))predicate __attribute__((swift_name("removeFirstIfIsInstanceOfOrPeekIf(predicate:)")));
- (MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable)removeFirstOrNull __attribute__((swift_name("removeFirstOrNull()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL isRemoved __attribute__((swift_name("isRemoved")));
@property (readonly, getter=next_) id _Nullable next __attribute__((swift_name("next")));
@property (readonly) MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *nextNode __attribute__((swift_name("nextNode")));
@property (readonly) MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *prevNode __attribute__((swift_name("prevNode")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreLockFreeLinkedListNode.AbstractAtomicDesc")))
@interface MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNodeAbstractAtomicDesc : MiamCoreKotlinx_coroutines_coreAtomicDesc
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)completeOp:(MiamCoreKotlinx_coroutines_coreAtomicOp<id> *)op failure:(id _Nullable)failure __attribute__((swift_name("complete(op:failure:)")));
- (id _Nullable)failureAffected:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable)affected __attribute__((swift_name("failure(affected:)")));
- (void)finishOnSuccessAffected:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)next __attribute__((swift_name("finishOnSuccess(affected:next:)")));
- (void)finishPreparePrepareOp:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNodePrepareOp *)prepareOp __attribute__((swift_name("finishPrepare(prepareOp:)")));
- (id _Nullable)onPreparePrepareOp:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNodePrepareOp *)prepareOp __attribute__((swift_name("onPrepare(prepareOp:)")));
- (void)onRemovedAffected:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)affected __attribute__((swift_name("onRemoved(affected:)")));
- (id _Nullable)prepareOp:(MiamCoreKotlinx_coroutines_coreAtomicOp<id> *)op __attribute__((swift_name("prepare(op:)")));
- (BOOL)retryAffected:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(id)next __attribute__((swift_name("retry(affected:next:)")));
- (MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable)takeAffectedNodeOp:(MiamCoreKotlinx_coroutines_coreOpDescriptor *)op __attribute__((swift_name("takeAffectedNode(op:)")));
- (id)updatedNextAffected:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)next __attribute__((swift_name("updatedNext(affected:next:)")));
@property (readonly) MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable affectedNode __attribute__((swift_name("affectedNode")));
@property (readonly) MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable originalNext __attribute__((swift_name("originalNext")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreLockFreeLinkedListNodeAddLastDesc")))
@interface MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNodeAddLastDesc<T> : MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNodeAbstractAtomicDesc
- (instancetype)initWithQueue:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)queue node:(T)node __attribute__((swift_name("init(queue:node:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (void)finishOnSuccessAffected:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)next __attribute__((swift_name("finishOnSuccess(affected:next:)")));
- (void)finishPreparePrepareOp:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNodePrepareOp *)prepareOp __attribute__((swift_name("finishPrepare(prepareOp:)")));
- (BOOL)retryAffected:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(id)next __attribute__((swift_name("retry(affected:next:)")));
- (MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable)takeAffectedNodeOp:(MiamCoreKotlinx_coroutines_coreOpDescriptor *)op __attribute__((swift_name("takeAffectedNode(op:)")));
- (id)updatedNextAffected:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)next __attribute__((swift_name("updatedNext(affected:next:)")));
@property (readonly) MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable affectedNode __attribute__((swift_name("affectedNode")));
@property (readonly) T node __attribute__((swift_name("node")));
@property (readonly) MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *originalNext __attribute__((swift_name("originalNext")));
@property (readonly) MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *queue __attribute__((swift_name("queue")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreLockFreeLinkedListNodeRemoveFirstDesc")))
@interface MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNodeRemoveFirstDesc<T> : MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNodeAbstractAtomicDesc
- (instancetype)initWithQueue:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)queue __attribute__((swift_name("init(queue:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (id _Nullable)failureAffected:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable)affected __attribute__((swift_name("failure(affected:)")));
- (void)finishOnSuccessAffected:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)next __attribute__((swift_name("finishOnSuccess(affected:next:)")));
- (void)finishPreparePrepareOp:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNodePrepareOp *)prepareOp __attribute__((swift_name("finishPrepare(prepareOp:)")));
- (BOOL)retryAffected:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(id)next __attribute__((swift_name("retry(affected:next:)")));
- (MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable)takeAffectedNodeOp:(MiamCoreKotlinx_coroutines_coreOpDescriptor *)op __attribute__((swift_name("takeAffectedNode(op:)")));
- (id)updatedNextAffected:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *)next __attribute__((swift_name("updatedNext(affected:next:)")));
@property (readonly) MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable affectedNode __attribute__((swift_name("affectedNode")));
@property (readonly) MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable originalNext __attribute__((swift_name("originalNext")));
@property (readonly) MiamCoreKotlinx_coroutines_coreLockFreeLinkedListNode *queue __attribute__((swift_name("queue")));
@property (readonly) T _Nullable result __attribute__((swift_name("result")));
@end;

#pragma pop_macro("_Nullable_result")
#pragma clang diagnostic pop
NS_ASSUME_NONNULL_END
